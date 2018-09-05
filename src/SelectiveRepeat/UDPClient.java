/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelectiveRepeat;

import StopAndWait.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author willi
 */
public class UDPClient {

    private InetAddress IPAddress;
    private DatagramSocket clientSocket;
    private Mensaje[] buffer;
    private Mensaje[] window;
    private int c;

    public UDPClient() throws UnknownHostException, SocketException {
        IPAddress = InetAddress.getByName("localhost");
        clientSocket = new DatagramSocket();
        
    }

    public void slidingWindow() {

        for (int i = 0; i < window.length; i++) {
            if ((c + i) < buffer.length) {
                window[i] = buffer[c + i];
            } else {
                window[i] = null;
            }
        }
        c++;
    }

    public void run() throws SocketException, UnknownHostException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        //ArrayList<byte[]> data = new ArrayList<>();
        System.out.println("Enter no of frames to be sent:");
        int frames = sc.nextInt();
        System.out.println("# frames " + frames);
        buffer = new Mensaje[frames];
        window = new Mensaje[4];
        c = 1;
        for (int i = 0; i < frames; i++) {
            Mensaje m = new Mensaje("" + i);
            buffer[i] = m;
            if (i < 4) {
                window[i] = m;
            }
        }
        for (int i = 0; i < window.length; i++) {
            enviar(window[i]);
        }
        do {
            String ack = recibir();
            if (ack != null) {
                System.out.println("FROM SERVER: ACK " + ack);
                for (int i = 0; i < window.length; i++) {
                    if (window[i] != null) {
                        if (window[i].getMensaje().equals(ack.trim())) {
                            Mensaje mens = window[i];
                            mens.cancelTimer();
                            slidingWindow();
                        }
                    }
                }
                for (int i = 0; i < window.length; i++) {
                    if(window[i]!=null){
                    System.out.println(window[i].getMensaje());
                    }else{
                        System.out.println("-");
                    }
                }
                if (window[window.length - 1] != null) {
                    enviar(window[window.length - 1]);
                }
            } else {

            }
        } while (true);
        //enviar("exit");
    }

    public String recibir() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String re = new String(receivePacket.getData());
        return re;
    }

    public void enviar(Mensaje m) throws IOException {
        m.addtask(new Task(this, m), 2000);
        System.out.println(" send frame: " + m.getMensaje());
        DatagramPacket sendPacket = new DatagramPacket(m.getMensaje().getBytes(), m.getMensaje().getBytes().length, IPAddress, 9876);
        clientSocket.send(sendPacket);
    }

    public static void main(String args[]) throws Exception {
        UDPClient client = new UDPClient();
        client.run();
    }
}
