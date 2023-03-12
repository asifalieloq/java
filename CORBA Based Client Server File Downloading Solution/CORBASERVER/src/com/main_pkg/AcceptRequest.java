//
package com.main_pkg;

import common.DBConfiguration;
import static common.DBConfiguration.conn;
import static common.DBConfiguration.stmt;
import common.DBShareFiles;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.h2.engine.Setting;

public class AcceptRequest implements Initializable {

    public static MydbSetting softwareSetting= new MydbSetting();

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField url;
    @FXML
    private TextField username;
    @FXML
    private TextField psd;
    @FXML
    private Button ConnectDatabase;
    @FXML
    private Label msg_show;
    @FXML
    private Button ResetDBSetting;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Process runtime = Runtime.getRuntime().exec("ORBD ORBInitialPort 4444");
        } catch (Exception ex) {
            Logger.getLogger(AcceptRequest.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        MydbSetting obj = null;
        try {

            try {
                obj = LocalFileHandling.readsSettings();
            } catch (FileNotFoundException ex) {
                System.out.println("sssss");
                Logger.getLogger(AcceptRequest.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
            if (obj != null) {
                this.url.setText(obj.url);
                this.username.setText(obj.username);
                this.psd.setText(obj.password);
            }


        } catch (Exception ex) {
            Logger.getLogger(AcceptRequest.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    @FXML
    private void connect(ActionEvent event) {
        msg_show.setText("");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url.getText(), username.getText(), psd.getText());
            stmt = conn.createStatement();
            DBShareFiles dbsf = new DBShareFiles();
            dbsf.createTable();
            msg_show.setText("Database connected successfully");
            softwareSetting= new MydbSetting();
            softwareSetting.url = this.url.getText();
            softwareSetting.username = this.username.getText();
            softwareSetting.password = this.psd.getText();
            LocalFileHandling.writesSettings(softwareSetting);
            
        } catch (ClassNotFoundException | SQLException ex) {
            msg_show.setText("Database connectivity failed");
            Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    @FXML
    private void ResetSetting(ActionEvent event) {
        this.url.setText("jdbc:h2:file:./REPO/DB");
        this.username.setText("owner");
        this.psd.setText("1213");

    }

}
