package SerVerMain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asif Ali
 */
//import FileReciever.FileReceiver;
import FileShareApp.*;
import common.DBConfiguration;
import common.DBShareFiles;
import common.ShareFiles;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StartServer {

    public static ObservableList<ShareFiles> filesList = FXCollections.observableArrayList();
    public static DBShareFiles dbsf = new DBShareFiles();
    public static int totalFiles = filesList.size();

    public static void main(String args[]) {
        DBConfiguration dbConfiguration = new DBConfiguration(0, true);
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            fileshareObj helloImpl = new fileshareObj();
            helloImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            fileshare href = fileshareHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "Hello";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("HelloServer ready and waiting ...");
            filesList.remove(0, filesList.size());
            filesList = dbsf.loadGroupData(filesList, "");
            totalFiles = filesList.size();

            test();
            // wait for invocations from clients
            for (;;) {
                orb.run();
                args = new String[]{"C:\\Users\\Asif Ali\\Documents\\FileToSend\\paer.png"};
                //       FileSender.FileSender.main(args);
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("HelloServer Exiting ...");

    }



    public static void test() {
      /*  totalFiles = dbsf.getFilesCount("");
        //test insertion
        for (int i = 0; i < totalFiles; i++) {
            ShareFiles s = filesList.get(i);
            System.out.println("Client ID:" + s.getClientId());
            System.out.println("File ID:" + s.getFileId());

            //               System.out.println("Inserted: File Name:" + s.getFileName());
        }
        System.out.println("Total files after print in server:" + totalFiles);
        //test insertion
        for (int i = 0; i < 9; i++) {
            ShareFiles s = new ShareFiles("client id" + i, "ipaddress", 8, "client name" + i, "File Name" + i, "File Location", "NA");
            dbsf.insertObject(s);
            //               System.out.println("Inserted: File Name:" + s.getFileName());
        }
        totalFiles = dbsf.getFilesCount("");
        System.out.println("Total files after list insertion test in server:" + totalFiles);
        //single delete test
        ShareFiles s = new ShareFiles("client id" + 5, "ipaddress", 8, "client name" + 5, "File Name" + 5, "NA", "NA");
        dbsf.deleteObject(s);
        totalFiles = dbsf.getFilesCount("");
        System.out.println("Total files after single delete test in server:" + totalFiles);

        //test deletion
        for (int i = 0; i < totalFiles / 2; i++) {
            s = new ShareFiles("client id" + i, "ipaddress", 8, "client name" + i, "File Name" + i, "NA", "NA");
            dbsf.deleteObject(s);
            //               System.out.println("Deleted: File Name:" + s.getFileName());
        }
        totalFiles = dbsf.getFilesCount("");
        System.out.println("Total files after deletion in server:" + totalFiles);

              ShareFiles sf_new = new ShareFiles();
              sf_new.setFileId(2314);
              sf_new = StartServer.dbsf.findindDatabase(sf_new);
              System.out.println(sf_new.toString());

    */
      

    }
}
