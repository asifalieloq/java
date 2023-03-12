package com.main_pkg;

import SerVerMain.StartServer;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends Application {

    private static Scene scene;
    private static Parent root;
    private String[] args = new String[]{""};
    public static Dimension screenSize;
    public static Dimension screenSizeFull;
    public static Stage appStage;

    public void start(Stage stage) throws IOException {
        // args= new String[]{"-ORBInitialPort", "4444", "-ORBInitialHost", "localhost"};

        stage.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StartServer.main(args);
            }
        }).start();
        root = (Parent) FXMLLoader.load(getClass().getResource("AcceptRequest.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        appStage = stage;
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
        scene = new Scene(root);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return (Parent) fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
