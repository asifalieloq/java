package ShareFiles;

import static ShareFiles.TVCShareFiles.objList;
import static connectClient.StartClient.helloImpl;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.ZoneId;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.StringHolder;

/**
 * FXML Controller class
 *
 * @author Asif
 */
public class FDCShareFiles implements Initializable {

    @FXML
    private TextField tf_filterfileId;
    @FXML
    private CheckBox filter_cbfileId;
    @FXML
    private Button bfCancel;
    @FXML
    private Button bfDoFilt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> fproductionIDOptions = FXCollections.observableArrayList("OR", "AND");
        // TODO
    }

    @FXML
    private void fCancelA(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void bfDoFiltA(ActionEvent event) {
        int fileIdd = Integer.parseInt(this.tf_filterfileId.getText());
        StringHolder argclientId = new StringHolder("");
        StringHolder argipAddress = new StringHolder("");
        IntHolder argportNumber = new IntHolder(3);
        StringHolder argclientName = new StringHolder("");
        StringHolder argfileName = new StringHolder("");
        StringHolder argfileLocation = new StringHolder("");
        StringHolder argfileImage = new StringHolder("");
        IntHolder argfileId = new IntHolder(0);
        String resp = helloImpl.printFiles(fileIdd, argclientId, argipAddress, argportNumber, argclientName, argfileId, argfileName, argfileLocation, argfileImage);
        ShareFiles cf = new ShareFiles(argfileId.value, argclientId.value, argipAddress.value, argportNumber.value, argclientName.value, argfileName.value, argfileLocation.value, argfileImage.value);
        objList.remove(0, objList.size());
        objList.add(cf);
        
    }
}
