package com.main_pkg;

import ServerStatus.serverstatus_c;
import ShareFiles.ShareFiles;
import ShareFiles.TVCShareFiles;
import com.jfoenix.controls.JFXButton;
import static connectClient.StartClient.helloImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DashBoardController implements Initializable {

    @FXML
    public VBox MainAnchor;
    @FXML
    private VBox AnchorPaneMain;
    @FXML
    private VBox MenueAnchor;
    @FXML
    private VBox MenuButtonBox;
    @FXML
    private Hyperlink loginId;
    String css, filtercss;
    @FXML
    private JFXButton RemoteServer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        css = App.class.getResource("CommonStyle/CustomStyles.css").toExternalForm();
        filtercss = App.class.getResource("CommonStyle/FilterStyles.css").toExternalForm();
        this.MainAnchor.getStylesheets().add(css);
        if (App.SERVER_STATUS == 1) {
            sharefiles(null);
        } else {
            RemoteServer(null);
        }
        this.MainAnchor.setMaxWidth(App.screenSze.width);
        this.MainAnchor.setMaxHeight(App.screenSze.height);
        this.MenuButtonBox.setPrefHeight((App.screenSze.height - 140));
        App.dashBoardController = this;

    }

    @FXML
    private void sharefiles(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShareFiles.class.getResource("TViewShareFiles.fxml"));
            this.MainAnchor.getChildren().clear();
            Node node = (Node) fxmlLoader.load();
            this.MainAnchor.getChildren().add(node);
            FadeTransition ft = new FadeTransition(Duration.millis(1500.0D));
            ft.setNode(node);
            ft.setFromValue(0.1D);
            ft.setToValue(1.0D);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
            System.out.println("Refresh Called");
            try {
                if (App.SERVER_STATUS == 1) {
                    helloImpl.refreshList();
                    TVCShareFiles.loadData();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
            }
        } catch (IOException ex) {

            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    @FXML
    private void AccessFiles(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TVCShareFiles.class.getResource("TViewShareFiles.fxml"));
            this.MainAnchor.getChildren().clear();
            Node node = (Node) fxmlLoader.load();
            this.MainAnchor.getChildren().add(node);
            FadeTransition ft = new FadeTransition(Duration.millis(1500.0D));
            ft.setNode(node);
            ft.setFromValue(0.1D);
            ft.setToValue(1.0D);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
            System.out.println("Refresh Called");
            helloImpl.refreshList();
            TVCShareFiles.loadData();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }

    }

    @FXML
    private void RemoteServer(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(serverstatus_c.class.getResource("serverstatus.fxml"));
        try {
            this.MainAnchor.getChildren().clear();
            Node node = (Node) fxmlLoader.load();
            this.MainAnchor.getChildren().add(node);
            FadeTransition ft = new FadeTransition(Duration.millis(1500.0D));
            ft.setNode(node);
            ft.setFromValue(0.1D);
            ft.setToValue(1.0D);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

}
