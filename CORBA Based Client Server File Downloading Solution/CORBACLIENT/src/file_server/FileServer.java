/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
 
public class FileServer implements Runnable {
    Socket socket;
    String filePath; 
    
    public static final int FILE_NAME_LNE = 128;
    
    
    public FileServer(Socket socket, String filePath) {
        super();
        this.socket = socket;
        this.filePath = filePath;            
    }
    
    public void sendFile() {
        
        File file = new File(filePath);
        byte[] buffer = new byte[1024];
        int readLen = 0;
        String fileName = file.getName();
        System.out.println("FileName : " + fileName);
        byte[] fileNameByte = new byte[128];
        System.arraycopy(fileName.getBytes(), 0, fileNameByte, 0,  fileName.getBytes().length);
 
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(fileNameByte, 0, 100);
            bos.flush();
            bos.write(fileNameByte, 100, 28);
            bos.flush();
            
            while((readLen = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, readLen);                
            }
 
            bis.close();
            bos.close();
            socket.close();
 
 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
    @Override
    public void run() {
        // TODO Auto-generated method stub
        sendFile();
    }
    
}
