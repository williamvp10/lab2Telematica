/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelectiveRepeat;

import StopAndWait.*;
import java.net.*;

/**
 *
 * @author willi
 */
public class UDPServer {

    public static void main(String args[]) throws Exception {
        String str = "exit";
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            if (sentence.trim().equals(str)) {
                System.out.println("terminooo");
                break;
            } else {
                System.out.println("Frame " + sentence + " was received");
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                String capitalizedSentence = "" + sentence;
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                serverSocket.send(sendPacket);
                System.out.println("ACK Frame " + sentence + " send");
            }
            // Thread.sleep(40);
        }
    }
}
