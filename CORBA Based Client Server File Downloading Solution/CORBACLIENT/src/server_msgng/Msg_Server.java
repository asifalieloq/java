/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_msgng;

/**
 *
 * @author Asif Ali
 */
import file_client.FileRecieverMain;
import file_server.FileServerMain;
import java.net.*;
import java.io.*;

public class Msg_Server {

    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Msg_Server(int port) {
        try {
            server = new ServerSocket(port);
                System.out.println("Starting messaging server....");
            for (;;) {
                socket = server.accept();
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                FileServerMain.file_name = in.readUTF();
                FileServerMain.IP_ADDRESS = in.readUTF();
                FileServerMain.PORT = in.readInt();
                System.out.println("File Name:" + FileServerMain.file_name);
                System.out.println("Ip Address:"+FileServerMain.IP_ADDRESS );
                if (FileServerMain.file_name != null) {
                    FileServerMain.main(null);
                }

                socket.close();
                in.close();
            }
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Msg_Server server = new Msg_Server(5000);
            }
        }).start();
    }

}
