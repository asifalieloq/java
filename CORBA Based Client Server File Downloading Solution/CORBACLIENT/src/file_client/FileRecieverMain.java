/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_client;

import com.main_pkg.App;

public class FileRecieverMain {

    public static int STATUS = 0;
    public static int PORT = 12345;

    public FileRecieverMain() {
        System.out.println("Status:"+STATUS);
        if (STATUS != 1) {
            FSBrocker receiver = new FSBrocker();
            STATUS = 1;
           
            receiver.acceptConnect();
            
        }

    }

    public static void main(String[] args) {
        System.out.println("Main thread");
          App.FILE_DOWNLOAD_STATUS=1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                    new FileRecieverMain();
            }
        }).start();
    }
}
