
package AcceptRequest;

import file_server.FileServerMain;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AcceptRequest implements Initializable {

    static ServerSettings softwareSetting;

    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        //    FileServerMain.main(null);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

}
