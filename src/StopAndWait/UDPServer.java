/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopAndWait;

import java.net.*;

/**
 *
 * @author willi
 */
public class UDPServer {
    
    

    public static void main(String args[]) throws Exception {
        String str = "exit";
        int ack = 0;
        int val = 0,val2=0;
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
            }
            Thread.sleep(40);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
           
            if ((Integer.parseInt(sentence.trim())%2) == 0) {
                ack = 0;
            } else {
                ack = 1;
            }
             String capitalizedSentence =""+ ack;
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            if(Integer.parseInt(sentence.trim())==4 &&val2==0 ){
                val=1; val2=1;
            }
            if(val!=1){
            serverSocket.send(sendPacket);
            }else{
                val=0;
            }
            System.out.println("ACK Frame " + sentence + " send");
        }
    }
}
