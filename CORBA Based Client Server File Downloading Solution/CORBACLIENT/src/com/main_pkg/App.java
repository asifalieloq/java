package com.main_pkg;

import connectClient.StartClient;
import file_server.FileServerMain;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import server_msgng.Msg_Server;

public class App extends Application {

    public static DashBoardController dashBoardController;
    private static Scene scene;
    private static Parent root;
    public static Dimension screenSize;
    public static Dimension screenSizeFull;
    public static Point screenCenter;
    public static int go_back = 0;
    public static Stage appStage;
    public static Image softlog;
    public static String HWD_DIECTORY = "C://Users";
    public static String SEND_FILE_DIRECTORY;
    public static String DOWNLOAD_FILE_DIRECTORY;
    public static int SERVER_STATUS = 0;
    public static int FILE_DOWNLOAD_STATUS = 0;
    private String[] args = new String[]{""};
    public static Dimension screenSze = Toolkit.getDefaultToolkit().getScreenSize();

    public void start(Stage stage) throws IOException {
        // args= new String[]{"-ORBInitialPort", "4444", "-ORBInitialHost", "localhost"};
        SEND_FILE_DIRECTORY = (new File(".")).getCanonicalPath() + File.separator + "FILES\\";
        DOWNLOAD_FILE_DIRECTORY = (new File(".")).getCanonicalPath() + File.separator + "RECV\\";

        System.out.println("Connecting  server");
        try {
            StartClient.main(args);
            Msg_Server.main(args);
            SERVER_STATUS = 1;
        } catch (Exception ex) {
            SERVER_STATUS = 0;
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
        try {
            FileServerMain.main(null);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
        
        root = (Parent) FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        appStage = stage;
        appStage.setMaxWidth(screenSze.width);
        appStage.setMaxHeight(screenSze.height);
        stage.show();
        appStage.setOnCloseRequest(e -> {
            e.consume();
            System.exit(0);
        });
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void logout(String fxml) throws IOException {

    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return (Parent) fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
