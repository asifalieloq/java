/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_client;

import static ShareFiles.FCShareFiles.dir;
import com.main_pkg.App;
import static com.main_pkg.App.FILE_DOWNLOAD_STATUS;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReciever implements Runnable {

    Socket socket;

    public FileReciever(Socket socket) {
        super();
        this.socket = socket;
    }

    public void getFile() {
        byte[] buffer = new byte[1024];
        byte[] byteFileName = new byte[128];

        int readLen = 0;

        BufferedInputStream bis;
        BufferedOutputStream bos;

        try {
            bis = new BufferedInputStream(socket.getInputStream());

            int totalLen = 0;

            while (totalLen < 128) {

                readLen = bis.read(byteFileName, totalLen, 128 - totalLen);
                if (readLen == -1) {
                    return;
                }
                System.out.println("ReadLen :" + readLen);
                totalLen += readLen;

            }

            String fileName = new String(byteFileName);

            System.out.println("FileName : [" + fileName + "]");

            fileName = fileName.trim();
            
            System.out.println("FileName : [" + fileName + "]");

            File file = new File("./RECV/" + fileName);

            bos = new BufferedOutputStream(new FileOutputStream(file));

            while ((readLen = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, readLen);
            }

            App.FILE_DOWNLOAD_STATUS = 1;

            bis.close();
            bis = null;
            bos.close();
            bos = null;
            System.out.println("socket : " + socket.hashCode());
            //socket.close();            
        } catch (IOException e) {
            FILE_DOWNLOAD_STATUS = 0;
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        getFile();
    }

}
