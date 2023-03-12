package ServerStatus;

import AcceptRequest.*;
import com.main_pkg.App;
import connectClient.StartClient;
import static connectClient.StartClient.helloImpl;
import file_server.FileServerMain;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class serverstatus_c implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField S_IPAddress;
    @FXML
    private TextField S_PortNumber;
    @FXML
    private CheckBox cbLocalServer;
    @FXML
    private CheckBox cb_remoteServer;

    static ServerSettings srvrSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerSettings obj = null;
        try {
            obj = LocalFileHandling.readsSettings();
        } catch (FileNotFoundException ex) {
            System.out.println("sssss");
            Logger.getLogger(AcceptRequest.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        if (obj != null) {
            this.S_IPAddress.setText(obj.getServerIP());
            this.S_PortNumber.setText(obj.getServerPortNumber());
        }
    }

    @FXML
    private void ConnectToServer(ActionEvent event) {
        srvrSettings = new ServerSettings();

        try {

            if (this.cb_remoteServer.isSelected()) {
                try {
                    String args[] = new String[]{"-ORBInitialPort", this.S_PortNumber.getText(), "-ORBInitialHost", this.S_IPAddress.getText()};
                    StartClient.main(args);
                    helloImpl.refreshList();
                    Notifications.create().title("Server Connecting...").text("Connected with remote server").hideAfter(Duration.seconds(4.0D)).darkStyle().show();
                } catch (Exception ex) {
                    Notifications.create().title("Server Connecting...").text("Remote server access failed. " + ex).hideAfter(Duration.seconds(4.0D)).darkStyle().show();
                    Notifications.create().title("Data Entry Error").text("Please provide both IP address and port number properly" + ex).hideAfter(Duration.seconds(4.0D)).darkStyle().show();
                }
            }
            if (this.cbLocalServer.isSelected()) {
                try {
                    String args[] = new String[]{};
                    StartClient.main(args);
                    helloImpl.refreshList();
                    Notifications.create().title("Server Connecting...").text("Connected with local host server").hideAfter(Duration.seconds(4.0D)).darkStyle().show();
                } catch (Exception ex) {
                    Notifications.create().title("Server Connecting...").text("Local Host server access failed").hideAfter(Duration.seconds(4.0D)).darkStyle().show();
                }
            }
            srvrSettings.setServerIP(this.S_IPAddress.getText());
            srvrSettings.setServerPortNumber(this.S_PortNumber.getText());
            LocalFileHandling.writesSettings(srvrSettings);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    @FXML
    private void ResetSettings(ActionEvent event) {
        this.S_IPAddress.setText("");
        this.S_PortNumber.setText("");
        Notifications.create().title("Server Settings").text("Local server settings loaded").hideAfter(Duration.seconds(4.0D)).darkStyle().show();
        LocalFileHandling.writesSettings(srvrSettings);
    }

}
