/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2telematica;

import java.io.*;
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

            Thread.sleep(1000);
            
            if (sentence.trim().equals(str)) {
                System.out.println("terminooo");
                break;
            } else {
              System.out.println("Frame " + sentence + " was received");
            }
            Thread.sleep(4000);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            if(!sentence.trim().equals("3")){
            serverSocket.send(sendPacket);
            }
            System.out.println("ACK Frame " + sentence + " send");
        }
    }
}
