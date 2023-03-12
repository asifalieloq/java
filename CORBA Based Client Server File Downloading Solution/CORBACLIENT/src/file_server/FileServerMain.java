/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_server;

import static com.main_pkg.App.FILE_DOWNLOAD_STATUS;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileServerMain {

    public static int PORT = 12345;
    public static String FILES_DIRECTORY = "./FILES";
    public static String IP_ADDRESS = "127.0.0.1";
    public static String file_name;

    public FileServerMain() {
        Socket socket = null;
        ExecutorService service = Executors.newFixedThreadPool(5);
        File dir = new File(FILES_DIRECTORY);

        for (File file : dir.listFiles()) {
            if (file_name != null) {
                if (file_name.contains(file.getName())) {
                    String filePath = file.getAbsolutePath();
                    try {
                        socket = new Socket(IP_ADDRESS, PORT);
                    } catch (IOException e1) {
                        FILE_DOWNLOAD_STATUS = 0;
                        e1.printStackTrace();
                    }
                    if (socket.isConnected()) {
                        System.out.println("sender socket :" + socket.hashCode());
                        FileServer sender = new FileServer(socket, filePath);
                        service.execute(sender);
                    }
                } else {
                    //
                    String filePath = file.getAbsolutePath();
                    try {
                        socket = new Socket(IP_ADDRESS, PORT);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (socket.isConnected()) {
                        System.out.println("sender socket :" + socket.hashCode());
                        FileServer sender = new FileServer(socket, filePath);
                        service.execute(sender);
                    } 
                }
            }
        }
        service.shutdown();

    }

    public static void main(String[] args) {
        System.out.println("Main thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                new FileServerMain();
            }
        }).start();
    }
}
