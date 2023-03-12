/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcceptRequest;

import java.io.Serializable;

/**
 *
 * @author Asif Ali
 */
public class ServerSettings implements Serializable{
    String serverIP="";
    String serverPortNumber="";

    public ServerSettings() {
    }


    public void setServerPortNumber(String serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getServerPortNumber() {
        return serverPortNumber;
    }

    public String getServerIP() {
        return serverIP;
    }



    public void setUrl(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setUsername(String serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }
    
    
    
}
