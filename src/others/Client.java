package others;

import java.io.*;

import java.net.*;

import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception {

        Client client = new Client();

        client.run();

    }

    public void run() throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no of frames to be sent:");

        int n = sc.nextInt();

        Socket myskt = new Socket("localhost", 9999);

        PrintStream myps = new PrintStream(myskt.getOutputStream());

        for (int i = 0; i <= n;) {

            if (i == n) {

                myps.println("exit");

                break;

            }

            System.out.println("Frame number " + i + " is sent");

            myps.println(i);

            BufferedReader bf = new BufferedReader(new InputStreamReader(myskt.getInputStream()));

            String ack = bf.readLine();

            if (ack != null) {

                System.out.println("ACK was Received from receiver");

                i++;

                Thread.sleep(4000);

            } else {
                myps.println(i);
            }

        }

    }

}
