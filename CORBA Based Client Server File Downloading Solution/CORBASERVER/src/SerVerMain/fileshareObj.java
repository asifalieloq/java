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
//import Database.DBShareFiles;
//import Database.ShareFiles;
//import FileReciever.FileReceiver;
import common.DBShareFiles;
import common.ShareFiles;
import FileShareApp.*;
import static SerVerMain.StartServer.dbsf;
import static SerVerMain.StartServer.filesList;
import static SerVerMain.StartServer.totalFiles;

import org.omg.CORBA.*;

class fileshareObj extends filesharePOA {

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    /*
  // implement sayHello() method
  public String sayHello() {
    return "\nHello world !!\n";
  }
     */
    // implement shutdown() method
    @Override
    public void shutdown() {
        orb.shutdown(false);
    }

    @Override
    public String RegisterFile(String argclientId, String argipAddress, int argportNumber, String argclientName, String argfileName, String argfileLocation, String argfileImage) {
        ShareFiles sf_new = new ShareFiles(argclientId, argipAddress, argportNumber, argclientName, argfileName, argfileLocation, argfileImage);
        StartServer.dbsf.insertObject(sf_new);
        StartServer.totalFiles++;
        Last_File_Id = StartServer.dbsf.findLastInsertId();
        sf_new.setFileId(Last_File_Id);
        StartServer.filesList.add(sf_new);
        System.out.println("Total Files Awailable:" + StartServer.dbsf.getFilesCount(argfileImage));
        return "Successfully Added to database " + sf_new.toString();
    }

    @Override
    public String deleteFile(String argclientId, String argfileName) {
        ShareFiles sf_new = new ShareFiles();
        sf_new.setClientId(argclientId);
        sf_new.setFileName(argfileName);
        StartServer.dbsf.deleteObject(sf_new);
        for (int i = 0; i < StartServer.filesList.size(); i++) {
            if (sf_new.getFileId() == StartServer.filesList.get(i).getFileId()) {
                StartServer.filesList.remove(i);
                break;
            }
        }
        updateFilesList();
        return "File Deleted ClientId: " + argfileName + "File Name: " + argfileName;
    }

    @Override
    public String updateFile(int FileID, String argclientId, String argipAddress, int argportNumber, String argclientName, String argfileName, String argfileLocation, String argfileImage) {
        ShareFiles sf_new = new ShareFiles(argclientId, argipAddress, argportNumber, argclientName, argfileName, argfileLocation, argfileImage);
        sf_new.setFileId(FileID);
        String s = "Request to update file:" + sf_new.getFileId();
        StartServer.dbsf.updateObject(sf_new);
        updateFilesList();

        return s + "File Update" + sf_new.toString();

    }

    public static void updateFilesList() {
        filesList.remove(0, filesList.size());
        filesList = dbsf.loadGroupData(filesList, "");
    }

    @Override
    public int getTotalNumberOfFiles() {
        int total_Files = StartServer.dbsf.getFilesCount("");
        updateFilesList();
        listIndex = 0;
        return total_Files;
    }
    public int listIndex = 0;

    @Override
    public String listFile(int Index, IntHolder FileID, StringHolder argclientId, StringHolder argipAddress, IntHolder argportNumber, StringHolder argclientName, StringHolder argfileName, StringHolder argfileLocation, StringHolder argfileImage) {
        ShareFiles sf_new = new ShareFiles();
        if (Index >= 0 && Index < StartServer.filesList.size()) {
            sf_new = StartServer.filesList.get(Index);
            listIndex = Index;
        }

        if (sf_new != null) {
            argclientId.value = sf_new.getClientId();
            argipAddress.value = sf_new.getIpAddress();
            argportNumber.value = sf_new.getPortNumber();
            argclientName.value = sf_new.getClientName();
            FileID.value = sf_new.getFileId();
            argfileName.value = sf_new.getFileName();
            argfileLocation.value = sf_new.getFileLocation();
            argfileImage.value = sf_new.getFileImage();
        }
        return "File Listed: " + Index;
    }
    int Last_File_Id;

    @Override
    public String printFiles(int searchByFileId, StringHolder argclientId, StringHolder argipAddress, IntHolder argportNumber, StringHolder argclientName, IntHolder FileID, StringHolder argfileName, StringHolder argfileLocation, StringHolder argfileImage) {
        ShareFiles sf_new = new ShareFiles();
        sf_new.setFileId(searchByFileId);
        sf_new = StartServer.dbsf.findindDatabase(sf_new);
        if (sf_new != null) {
            argclientId.value = sf_new.getClientId();
            argipAddress.value = sf_new.getIpAddress();
            argportNumber.value = sf_new.getPortNumber();
            argclientName.value = sf_new.getClientName();
            FileID.value = sf_new.getFileId();
            argfileName.value = sf_new.getFileName();
            argfileLocation.value = sf_new.getFileLocation();
            argfileImage.value = sf_new.getFileImage();
        }
        return "File Searched and Retuned:" + argfileName.value;
    }

    @Override
    public int getLastFileID() {
        return Last_File_Id;
    }

    @Override
    public int refreshList() {
        filesList.remove(0, filesList.size());
        filesList = dbsf.loadGroupData(filesList, "");
        totalFiles = filesList.size();
        return totalFiles;
        //To change body of generated methods, choose Tools | Templates.
    }

}
