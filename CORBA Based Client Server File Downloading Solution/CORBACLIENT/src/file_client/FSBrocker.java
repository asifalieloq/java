/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_client;
import static file_client.FileRecieverMain.PORT;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class FSBrocker {
    public static int count;
    ServerSocket server;
    ExecutorService service ;
    public FSBrocker() {
        super();
        try {
            this.server = new ServerSocket(PORT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.count = 0;
    }
    
    public void acceptConnect() {
        Socket socket = null;
        
        service = Executors.newFixedThreadPool(10);
        
        try {
            while((socket = server.accept()) != null) {
                
                FileReciever receiver = new FileReciever(socket);                   
                
                service.execute(receiver);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        service.shutdown();
    
    }
}
