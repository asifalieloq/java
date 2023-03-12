package ShareFiles;

import static ShareFiles.FCShareFiles.editabelObj;
import static com.main_pkg.App.DOWNLOAD_FILE_DIRECTORY;
import static com.main_pkg.App.FILE_DOWNLOAD_STATUS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.collections.ObservableList;
import static connectClient.StartClient.helloImpl;
import file_client.FileRecieverMain;
import file_server.FileServerMain;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.StringHolder;
import server_msgng.Msg_Client;
import server_msgng.Msg_Server;

public class TVCShareFiles implements Initializable {

    @FXML
    private TableColumn<ShareFiles, String> clientId;

    @FXML
    private TableColumn<ShareFiles, String> ipAddress;

    @FXML
    private TableColumn<ShareFiles, Integer> portNumber;

    @FXML
    private TableColumn<ShareFiles, String> clientName;

    @FXML
    private TableColumn<ShareFiles, Integer> fileId;

    @FXML
    private TableColumn<ShareFiles, String> fileName;

    @FXML
    private TableColumn<ShareFiles, String> fileLocation;

    @FXML
    private TableColumn<ShareFiles, java.sql.Date> sharedDate;

    private TableColumn<ShareFiles, String> fileImage;
    @FXML
    private TableView<ShareFiles> myTableView;
    public static ObservableList<ShareFiles> objList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchor;
    public static ShareFiles curShareFiles;
    public static boolean isEdit = false;
    @FXML
    private HBox mainButtons;
    @FXML
    private Button bAddnew;
    @FXML
    private Button bfilter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        this.ipAddress.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        this.portNumber.setCellValueFactory(new PropertyValueFactory<>("portNumber"));
        this.clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        this.fileId.setCellValueFactory(new PropertyValueFactory<>("fileId"));
        this.fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.fileLocation.setCellValueFactory(new PropertyValueFactory<>("fileLocation"));
        this.sharedDate.setCellValueFactory(new PropertyValueFactory<>("sharedDate"));
        TableColumn select = new TableColumn("");
        select.setMinWidth(50);
        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ShareFiles, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<ShareFiles, CheckBox> arg0) {
                ShareFiles obj = arg0.getValue();
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.isSelect());
                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                            Boolean old_val, Boolean new_val) {
                        obj.setSelect(new_val);
                    }
                });
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        TableColumn<ShareFiles, Boolean> deleteCol = new TableColumn<>("");
        deleteCol.setCellValueFactory(new PropertyValueFactory<ShareFiles, Boolean>(""));
        deleteCol.setCellFactory((TableColumn<ShareFiles, Boolean> param) -> new Delete_File());
        TableColumn<ShareFiles, Boolean> editCol = new TableColumn<>("");
        editCol.setCellValueFactory(new PropertyValueFactory<ShareFiles, Boolean>(""));
        editCol.setCellFactory((TableColumn<ShareFiles, Boolean> param) -> new Edit_Data());
        TableColumn<ShareFiles, Boolean> viewCol = new TableColumn<>("Download File");
        viewCol.setCellValueFactory(new PropertyValueFactory<ShareFiles, Boolean>("Download File"));
        viewCol.setCellFactory((TableColumn<ShareFiles, Boolean> param) -> new HL_Download());
        select.setMaxWidth(30);
        deleteCol.setMaxWidth(35);
        editCol.setMaxWidth(35);
        viewCol.setMaxWidth(100);
        select.setMinWidth(30);
        deleteCol.setMinWidth(35);
        editCol.setMinWidth(35);
        viewCol.setMinWidth(100);
        myTableView.setEditable(true);
        myTableView.getColumns().add(0, editCol);
        myTableView.getColumns().add(0, deleteCol);
        myTableView.getColumns().add(0, select);
        myTableView.getColumns().add(viewCol);
        //  loadData();
        myTableView.setItems(objList);
    }

    public static void loadData() {
        int totalFiles = helloImpl.getTotalNumberOfFiles();
        System.out.println("Loading Files..");
        System.out.println("Total Files reported at client:" + totalFiles);
        objList.remove(0, objList.size());

        for (int i = 0; i < totalFiles; i++) {
            StringHolder argclientId = new StringHolder("");
            StringHolder argipAddress = new StringHolder("");
            IntHolder argportNumber = new IntHolder(3);
            StringHolder argclientName = new StringHolder("");
            StringHolder argfileName = new StringHolder("");
            StringHolder argfileLocation = new StringHolder("");
            StringHolder argfileImage = new StringHolder("");
            IntHolder argfileId = new IntHolder(0);
            String servResp = helloImpl.listFile(i, argfileId, argclientId, argipAddress, argportNumber, argclientName, argfileName, argfileLocation, argfileImage);
            ShareFiles cf = new ShareFiles(argfileId.value, argclientId.value, argipAddress.value, argportNumber.value, argclientName.value, argfileName.value, argfileLocation.value, argfileImage.value);
            objList.add(cf);
            System.out.println(i);
            System.out.println(servResp);
        }

    }

    @FXML
    private void bFilterA(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FDShareFiles.fxml"));
            Parent parent = fxmlLoader.load();
            FDCShareFiles dialogController = fxmlLoader.<FDCShareFiles>getController();
            // dialogController.setAppMainObservableList(tvObservableList);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private FXMLLoader fxmlLoaderA;

    private class Edit_Data<T> extends TableCell<T, Void> {

        private final Hyperlink link;

        public Edit_Data() {
            link = new Hyperlink("");
            link.getStyleClass().add("edit");
            link.setOnAction(evt -> {
                ShareFiles curObj = (ShareFiles) Edit_Data.this.getTableView().getItems().get(this.getIndex());
                if (curObj.isSelect() == true) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("FormShareFiles.fxml"));
                        anchor.getChildren().clear();
                        anchor.getChildren().add(fxmlLoader.load());
                        FCShareFiles f = fxmlLoader.getController();
                        FCShareFiles.getTo(curObj, f);
                        FCShareFiles.editabelObj = curObj;
                        f.setSpTop(1);
                    } catch (IOException ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Select Data");
                    alert.setContentText("Please select data first!");
                    alert.showAndWait();
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            setGraphic(empty ? null : link);
        }

    }

    private class HL_Download<T> extends TableCell<T, Void> {

        private final Hyperlink link;

        public HL_Download() {
            link = new Hyperlink("Download");
            link.setOnAction(evt -> {
                ShareFiles curObj = (ShareFiles) HL_Download.this.getTableView().getItems().get(this.getIndex());
                FILE_DOWNLOAD_STATUS = 0;
                StringHolder argclientId = new StringHolder("");
                StringHolder argipAddress = new StringHolder("");
                IntHolder argportNumber = new IntHolder(3);
                StringHolder argclientName = new StringHolder("");
                StringHolder argfileName = new StringHolder("");
                StringHolder argfileLocation = new StringHolder("");
                StringHolder argfileImage = new StringHolder("");
                IntHolder argfileId = new IntHolder(0);
                String ext = curObj.getFileName().substring(curObj.getFileName().lastIndexOf("."), curObj.getFileName().length());
                File f = new File("./RECV/" + curObj.getFileName() + ext);
                if (!f.exists()) {
                    org.controlsfx.control.Notifications.create().title("Downloading File...").text("You can find the file at location " + DOWNLOAD_FILE_DIRECTORY + " once dowload completes").darkStyle().show();
                    Msg_Client.DownLoadFile(curObj);
                    fileDownloadStatus(f);
                } else {
                    org.controlsfx.control.Notifications.create().title("File already existing").text("File already exists at location " + DOWNLOAD_FILE_DIRECTORY).darkStyle().show();
                }

            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : link);
        }
    }

    private class Delete_File<T> extends TableCell<T, Void> {

        private final Hyperlink link;

        public Delete_File() {
            link = new Hyperlink("");
            link.getStyleClass().add("delete");
            link.setOnAction(evt -> {
                ShareFiles obj = (ShareFiles) Delete_File.this.getTableView().getItems().get(this.getIndex());
                if (obj.isSelect() == true) {
                    System.out.println("Deleting file..." + obj.getClientId() + obj.getFileName());
                    String serv_response = helloImpl.deleteFile(obj.getClientId(), obj.getFileName());
                    getTableView().getItems().remove(getTableRow().getIndex());
                    System.out.println("Ser Response:" + serv_response);
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Select Data");
                    alert.setContentText("Please select data first!");
                    alert.showAndWait();
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            setGraphic(empty ? null : link);
        }

    }

    @FXML
    private void aAddnew(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("FormShareFiles.fxml"));
            anchor.getChildren().clear();
            anchor.getChildren().add(fxmlLoader.load());
            FCShareFiles f = fxmlLoader.getController();
            f.setSpTop(0);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    static int count = 1;

    public static void fileDownloadStatus(File f) {
        count = 0;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                while (!f.exists()) {
                    count++;
                    if (f.exists()) {

                        org.controlsfx.control.Notifications.create().title("Download Complete").text("Your file downloaded at location " + DOWNLOAD_FILE_DIRECTORY + "").darkStyle().show();
                        break;
                    }
                    if (count > 100) {
                        org.controlsfx.control.Notifications.create().title("Download Error!").text("Seems this file is not existing at the orignal location").darkStyle().show();
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TVCShareFiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
