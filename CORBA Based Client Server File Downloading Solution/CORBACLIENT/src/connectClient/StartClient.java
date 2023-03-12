package connectClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asif Ali
 */
import FileShareApp.fileshareHelper;
import FileShareApp.fileshare;
import ShareFiles.ShareFiles;
import com.main_pkg.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class StartClient {

    public static fileshare helloImpl;

    public static void main(String args[]) {

        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Hello";
            helloImpl = fileshareHelper.narrow(ncRef.resolve_str(name));
        } catch (Exception e) {
            App.SERVER_STATUS=0;
            e.printStackTrace(System.out);
        }
    }
    public static ObservableList<ShareFiles> filesList = FXCollections.observableArrayList();
    public static int totalFiles = filesList.size();

    public static void test() {
        totalFiles = helloImpl.getTotalNumberOfFiles();
        System.out.println("Total files after print in server:" + totalFiles);
        //test insertion
        for (int i = 0; i < 9; i++) {
            ShareFiles s = new ShareFiles(0, "client id" + i, "ipaddress", 8, "client name" + i, "File Name" + i, "File Location", "NA");
            helloImpl.RegisterFile(s.getClientId(), s.getIpAddress(), s.getPortNumber(), s.getClientName(), s.getFileName(), s.getFileLocation(), s.getFileImage());
            //               System.out.println("Inserted: File Name:" + s.getFileName());
        }
        totalFiles = helloImpl.getTotalNumberOfFiles();
        System.out.println("Total files after list insertion test in server:" + totalFiles);
        //single delete test
        ShareFiles s = new ShareFiles(8, "client id" + 5, "ipaddress", 8, "client name" + 5, "File Name" + 5, "NA", "NA");
        helloImpl.deleteFile(s.getClientId(), s.getFileName());
        totalFiles = helloImpl.getTotalNumberOfFiles();
        System.out.println("Total files after single delete test in server:" + totalFiles);

        //test deletion
        for (int i = 0; i < totalFiles / 2; i++) {
            s = new ShareFiles(0, "client id" + i, "ipaddress", 8, "client name" + i, "File Name" + i, "NA", "NA");
            helloImpl.deleteFile(s.getClientId(), s.getFileName());
            //               System.out.println("Deleted: File Name:" + s.getFileName());
        }
        totalFiles = helloImpl.getTotalNumberOfFiles();
        System.out.println("Total files after deletion in server:" + totalFiles);
    }
}
