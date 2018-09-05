/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopAndWait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author willi
 */
public class UDPClient {

    private InetAddress IPAddress;
    private DatagramSocket clientSocket;
    private Mensaje mensaje;

    public UDPClient() throws UnknownHostException, SocketException {
        IPAddress = InetAddress.getByName("localhost");
        clientSocket = new DatagramSocket();
        mensaje = new Mensaje("");
    }

    public void run() throws SocketException, UnknownHostException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        //ArrayList<byte[]> data = new ArrayList<>();
        System.out.println("Enter no of frames to be sent:");
        int frames = sc.nextInt();
        System.out.println("# frames" + frames);
        for (int i = 0; i <= frames;) {

            if (i == frames) {
                break;
            }
            enviar("" + i);
            String modifiedSentence = recibir();
            if (modifiedSentence != null) {
                System.out.println("FROM SERVER:" + modifiedSentence);
                i++;
                mensaje.cancelTimer();
                mensaje.purgeTimer();
            } else {

            }

        }
        //enviar("exit");
    }

    public String recibir() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String re = new String(receivePacket.getData());
        return re;
    }

    public void enviar(String data) throws IOException {
        mensaje.setMensaje(data);
        mensaje.addtask(new Task(this));
        System.out.println(" send frame: " + mensaje.getMensaje());
        DatagramPacket sendPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, IPAddress, 9876);
        clientSocket.send(sendPacket);
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public static void main(String args[]) throws Exception {
        UDPClient client = new UDPClient();
        client.run();
    }
}
