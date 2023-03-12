//
package ShareFiles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.CheckBox;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.layout.StackPane;
import org.controlsfx.validation.ValidationSupport;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;
import javafx.scene.control.TextArea;

import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.nio.file.StandardCopyOption;
import static ShareFiles.TVCShareFiles.objList;
import static ShareFiles.TVCShareFiles.isEdit;

import com.main_pkg.App;
import static com.main_pkg.App.SEND_FILE_DIRECTORY;
import static connectClient.StartClient.helloImpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.commons.io.FilenameUtils;

public class FCShareFiles implements Initializable {
    
    @FXML
    private TextField fClientId;
    
    @FXML
    private TextField fIpAddress;
    
    @FXML
    private TextField fPortNumber;
    
    @FXML
    private TextField fClientName;
    
    @FXML
    private TextField fFileName;
    
    @FXML
    private TextField fFileLocation;
    
    @FXML
    private DatePicker fSharedDate;
    
    @FXML
    private VBox fFileImage;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Label dateLabel;
    @FXML
    private StackPane SU_SP;
    public static ShareFiles editabelObj = new ShareFiles();
    @FXML
    private ImageView imageViewr;
    @FXML
    private Button chooseImage;
    @FXML
    private HBox SP_UO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fIpAddress.setText("127.0.0.1");
        dateLabel.setText(new java.util.Date().toString());
        fPortNumber.setText(String.valueOf(0));
        this.fSharedDate.setValue(new java.sql.Date(new java.util.Date().getTime()).toLocalDate());
        // this.fFileImage.getChildren().add(App.dashBoardController.ImageChooserAnchor);
    }
    
    public static ShareFiles setTo(FCShareFiles c) {
        try {
            ShareFiles o = new ShareFiles();
            o.setClientId(c.fClientId.getText());
            o.setIpAddress(c.fIpAddress.getText());
            o.setPortNumber(Integer.parseInt(c.fPortNumber.getText()));
            o.setClientName(c.fClientName.getText());
            o.setFileName(c.fFileName.getText());
            o.setFileLocation(c.fFileLocation.getText());
            o.setSharedDate(new java.sql.Date(java.util.Date.from(c.fSharedDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            String extension = dir.toString().substring(dir.toString().lastIndexOf("."), dir.toString().length());
            folder = new File(SEND_FILE_DIRECTORY + o.getFileName() + extension);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (dir != null) {
                if (dir.isFile()) {
                    try {
                        
                        Files.copy(dir.toPath(), folder.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        
                        if (folder.getName() != null) {
                            o.setFileImage(folder.getName() + FilenameUtils.getExtension(folder.getCanonicalPath()));
                        } else {
                            o.setFileImage("NA");
                        }
                    } catch (IOException ex) {
                        System.out.println("Exception while saving image! " + ex.getMessage());
                    }
                }
            }
            
            return o;
        } catch (Exception ex) {
            org.controlsfx.control.Notifications.create().title("Error in entry").text("Data Entry error:" + ex.getMessage()).hideAfter(javafx.util.Duration.seconds(4)).darkStyle().notify();
            Logger.getLogger(ShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void getTo(ShareFiles o, FCShareFiles c) {
        try {
            c.fClientId.setText(o.getClientId());
            c.fIpAddress.setText(o.getIpAddress());
            c.fPortNumber.setText(String.valueOf(o.getPortNumber()));
            c.fClientName.setText(o.getClientName());
            c.fFileName.setText(o.getFileName());
            c.fFileLocation.setText(o.getFileLocation());
            LocalDate dsharedDate = o.getSharedDate().toLocalDate();
            c.fSharedDate.setValue(dsharedDate);

            // App.dashBoardController.ShowImage(o.getFileImage(), imageViewer);
            //  App.dashBoardController.SetImageChooserLocation(1100, 200);
        } catch (Exception ex) {
            Logger.getLogger(ShareFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("TViewShareFiles.fxml"));
            anchor.getChildren().clear();
            anchor.getChildren().add(fxmlLoader.load());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void aCancel(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("TViewShareFiles.fxml"));
            anchor.getChildren().clear();
            anchor.getChildren().add(fxmlLoader.load());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void clearForm(ActionEvent event) {
        fClientId.setText("");
        fIpAddress.setText("");
        fPortNumber.setText("");
        fClientName.setText("");
        fFileName.setText("");
        fFileLocation.setText("");
        this.fSharedDate.setValue(new java.sql.Date(new java.util.Date().getTime()).toLocalDate());
        
    }
    
    @FXML
    private void resetForm(ActionEvent event) {
        if (editabelObj != null) {
            getTo(editabelObj, this);
            
        } else {
            ShareFiles c = new ShareFiles();
            getTo(c, this);
            
        }
    }
    
    @FXML
    private void submitForm(ActionEvent event) {
        if (validateForm() == true) {
            ShareFiles obj = setTo(this);
            if (obj != null) {
                String serv_response = helloImpl.RegisterFile(obj.getClientId(), obj.getIpAddress(), obj.getPortNumber(), obj.getClientName(), obj.getFileName(), obj.getFileLocation(), "Optional");
                obj.setFileId(helloImpl.getLastFileID());
                objList.add(obj);
                System.out.println("Ser Response:" + serv_response);
                int totalFiles = helloImpl.getTotalNumberOfFiles();
                System.out.println("Totlal files in server:" + totalFiles);
                clearForm(null);
            } else {
                org.controlsfx.control.Notifications.create().title("Error in entry").text("Duplicate Entry found").hideAfter(javafx.util.Duration.seconds(4)).darkStyle().notify();
            }
        }
    }
    
    @FXML
    private void updateForm(ActionEvent event) {
        ShareFiles obj = setTo(this);
        System.out.println("Updating File:" + obj.getFileId());
        obj.setFileId(editabelObj.getFileId());
        if (validateForm() == true) {
            objList.set(objList.indexOf(editabelObj), obj);
            editabelObj = obj;
            System.out.println("Updating File:" + obj.getFileId());
            String serv_response = helloImpl.updateFile(obj.getFileId(), obj.getClientId(), obj.getIpAddress(), obj.getPortNumber(), obj.getClientName(), obj.getFileName(), obj.getFileLocation(), "Optional");
            System.out.println("Ser Response:" + serv_response);
            goBack(null);
        }
    }
    
    public StackPane getSU_SP() {
        return SU_SP;
    }
    
    public void setSpTop(int index) {
        if (index == 1 || index == 0) {
            this.SU_SP.getChildren().get(index).toFront();
        }
    }
    
    final static ContextMenu formValidator[] = new ContextMenu[7];
    
    public boolean validateForm() {
        boolean check = true;
        if (this.fClientId.getText().isEmpty()) {
            formValidator[0] = new ContextMenu();
            formValidator[0].getItems().clear();
            formValidator[0].getItems().add(
                    new MenuItem("Please enter a valide Client Id value"));
            formValidator[0].show(this.fClientId, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fIpAddress.getText().isEmpty()) {
            formValidator[1] = new ContextMenu();
            formValidator[1].getItems().clear();
            formValidator[1].getItems().add(
                    new MenuItem("Please enter a valide Ip Address value"));
            formValidator[1].show(this.fIpAddress, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fPortNumber.getText().isEmpty()) {
            formValidator[2] = new ContextMenu();
            formValidator[2].getItems().clear();
            formValidator[2].getItems().add(
                    new MenuItem("Please enter a valide Port Number value"));
            formValidator[2].show(this.fPortNumber, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fClientName.getText().isEmpty()) {
            formValidator[3] = new ContextMenu();
            formValidator[3].getItems().clear();
            formValidator[3].getItems().add(
                    new MenuItem("Please enter a valide Client Name value"));
            formValidator[3].show(this.fClientName, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fFileName.getText().isEmpty()) {
            formValidator[4] = new ContextMenu();
            formValidator[4].getItems().clear();
            formValidator[4].getItems().add(
                    new MenuItem("Please enter a valide File Name value"));
            formValidator[4].show(this.fFileName, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fFileLocation.getText().isEmpty()) {
            formValidator[5] = new ContextMenu();
            formValidator[5].getItems().clear();
            formValidator[5].getItems().add(
                    new MenuItem("Please enter a valide File Location value"));
            formValidator[5].show(this.fFileLocation, Side.RIGHT, 10, 0);
            check = false;
        }
        if (this.fSharedDate.getValue() == null) {
            formValidator[6] = new ContextMenu();
            formValidator[6].getItems().clear();
            formValidator[6].getItems().add(
                    new MenuItem("Please enter a valide Shared Date value"));
            formValidator[6].show(this.fSharedDate, Side.RIGHT, 10, 0);
            check = false;
        }
        return check;
    }
    public static File folder;
    
    public static File dir;
    
    FileChooser fileChooser;
    
    @FXML
    private void chooseImageA(ActionEvent event) {
        InputStream stream = null;
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open Resource File");
        dir = this.fileChooser.showOpenDialog((Window) com.main_pkg.App.appStage);
        if (dir != null) {
            try {
                stream = new FileInputStream(dir.getAbsolutePath());
                Image image = new Image(stream);
                this.imageViewr.setImage(image);
                this.fFileName.setText(dir.getName());
                this.fFileLocation.setText(dir.getAbsolutePath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
            } 
        } 
        try {
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
    }
}
