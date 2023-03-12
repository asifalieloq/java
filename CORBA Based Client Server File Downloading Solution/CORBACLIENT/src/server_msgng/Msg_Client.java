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
import ShareFiles.ShareFiles;
import com.main_pkg.App;
import static com.main_pkg.App.FILE_DOWNLOAD_STATUS;
import file_client.FileRecieverMain;
import static file_client.FileRecieverMain.STATUS;
import file_server.FileServerMain;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Msg_Client {

    public static void DownLoadFile(ShareFiles cf) {
        System.out.println("Requesting file 0:" + FileServerMain.file_name);
        System.out.println(FileServerMain.IP_ADDRESS);
        FileServerMain.PORT = cf.getPortNumber();
        FileServerMain.file_name = cf.getFileName();
        main(null);
        FILE_DOWNLOAD_STATUS=1;
    }

    private Socket socket = null;
    private DataOutputStream out = null;
    private BufferedReader m_in = null;

    public Msg_Client(String address, int port) {
        System.out.println("Address:" + address);
        System.out.println("Requesting file:" + FileServerMain.file_name);

        try {
            try {
                socket = new Socket(address, 5000);
                System.out.println("Connected");

            } catch (UnknownHostException u) {
                System.out.println(u);
            } catch (IOException i) {
                System.out.println(i);
            }
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(FileServerMain.file_name);
            //InetAddress.getLocalHost().getHostAddress()
            out.writeUTF(address);
            out.writeInt(port);
            out.close();
            if (STATUS != 1) {
                
                FileRecieverMain.main(null);
            }

        } catch (IOException ex) {
            Logger.getLogger(Msg_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        System.out.println("Requesting file 1:" + FileServerMain.file_name);
        
        Msg_Client client = new Msg_Client(FileServerMain.IP_ADDRESS, FileServerMain.PORT);
        System.out.println("Requesting file 2:" + FileServerMain.file_name);

    }
}
