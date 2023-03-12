	
//


package ShareFiles;

import java.sql.Date;

public class ShareFiles implements Comparable {

    private String clientId = null;

    private String ipAddress = null;

    private int portNumber = 0;

    private String clientName = null;

    private int fileId = 0;

    private String fileName = null;

    private String fileLocation = null;

    private java.sql.Date sharedDate = new java.sql.Date(new java.util.Date().getTime());

    private String fileImage = null;

    public ShareFiles() {
    }

    public ShareFiles(String argclientId, String argipAddress, int argportNumber, String argclientName, int argfileId, String argfileName, String argfileLocation, java.sql.Date argsharedDate, String argfileImage) {
        clientId = argclientId;
        ipAddress = argipAddress;
        portNumber = argportNumber;
        clientName = argclientName;
        fileId = argfileId;
        fileName = argfileName;
        fileLocation = argfileLocation;
        sharedDate = argsharedDate;
        fileImage = argfileImage;

    }



    public ShareFiles(int value, String value0, String value1, int value2, String value3, String value4, String value5, String value6) {
        clientId = value0;
        ipAddress = value1;
        portNumber = value2;
        clientName = value3;
        fileName = value4;
        fileLocation = value5;
        fileImage = value6;
        fileId=value;
        System.out.println("File ID:"+fileId);

    }

   
    public void setClientId(String argclientId) {
        clientId = argclientId;
    }

    public void setIpAddress(String argipAddress) {
        ipAddress = argipAddress;
    }

    public void setPortNumber(int argportNumber) {
        portNumber = argportNumber;
    }

    public void setClientName(String argclientName) {
        clientName = argclientName;
    }

    public void setFileId(int argfileId) {
        fileId = argfileId;
    }

    public void setFileName(String argfileName) {
        fileName = argfileName;
    }

    public void setFileLocation(String argfileLocation) {
        fileLocation = argfileLocation;
    }

    public void setSharedDate(java.sql.Date argsharedDate) {
        sharedDate = argsharedDate;
    }

    public void setFileImage(String argfileImage) {
        fileImage = argfileImage;
    }

    public String getClientId() {
        return clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public int getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public java.sql.Date getSharedDate() {
        return sharedDate;
    }

    public String getFileImage() {
        return fileImage;
    }

    @Override
    public String toString() {
        return this.clientId + "" + this.ipAddress + "" + this.portNumber + "" + this.clientName + "" + this.fileId + "" + this.fileName + "" + this.fileLocation + "" + this.sharedDate + "" + this.fileImage;
    }

    @Override
    public int compareTo(Object o) {
        int mod = 0;
        ShareFiles a = (ShareFiles) o;
        ShareFiles b = this;
        if (mod == 0) {
            return a.clientId.compareTo(b.clientId);
        } else if (mod == 1) {
            return a.ipAddress.compareTo(b.ipAddress);
        } else if (mod == 2) {
            if (a.portNumber > b.portNumber) {
                return -1;
            } else if (a.portNumber < b.portNumber) {
                return 1;
            } else {
                return 0;
            }
        } else if (mod == 3) {
            return a.clientName.compareTo(b.clientName);
        } else if (mod == 4) {
            if (a.fileId > b.fileId) {
                return -1;
            } else if (a.fileId < b.fileId) {
                return 1;
            } else {
                return 0;
            }
        } else if (mod == 5) {
            return a.fileName.compareTo(b.fileName);
        } else if (mod == 6) {
            return a.fileLocation.compareTo(b.fileLocation);
        } else if (mod == 8) {
            return a.fileImage.compareTo(b.fileImage);
        }
        return 0;

    }

    //The select field given below is added by system. It is required 
    //if you want to do delete, edit or view operation on the object
    private boolean select = false;

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isSelect() {
        return select;
    }

}
