package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Hyperlink youTubeLink;
    @FXML
    private MenuItem goHomeMenuItem;
    @FXML
    private Hyperlink settingsLink;

    @FXML
    public void helpButtonAction(ActionEvent event) throws Exception {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/windows/helpwindow.fxml"));

        Scene scene = new Scene(root);
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("Java Core - финальное задание");
        window.show();

    }

    @FXML
    public void settingsButtonAction(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/windows/settingswindow.fxml"));
        Parent root = fxmlLoader1.load();
        SettingsController settingsController = (SettingsController) fxmlLoader1.getController();

        BorderPane borderPane = (BorderPane) ((Node) event.getSource()).getParent().getScene().getRoot();
        AnchorPane field = (AnchorPane) borderPane.getCenter();
        field.getChildren().clear();
        field.getChildren().addAll(root);

        settingsController.getUseCache().setText("" + Settings.isUseCache());
        settingsController.getCachePath().setText(Settings.getCachePath());
        settingsController.getLabelCurentCachePath().setText(Settings.getCachePath());
        settingsController.getShowLeadTime().setText("" + Settings.isShowLeadTime());

    }

    @FXML
    public void youTubeButtonAction(ActionEvent event) throws Exception {
        if (event.getSource() == youTubeLink) {
            Hyperlink hyperlinkYouTube = (Hyperlink) event.getSource();

            ContextMenu contextMenu = hyperlinkYouTube.getContextMenu();
            contextMenu.show(hyperlinkYouTube, Side.BOTTOM, 70, 0);
        }


    }

    @FXML
    public void goHomeAction(ActionEvent event) {
        showMainWindow(event);
    }

    @FXML
    public void $1Action(ActionEvent event) {
        requestShowGlobalInformation();
    }

    @FXML
    public void $2Action(ActionEvent event) {
        requestShowGlobalInformation();
    }

    @FXML
    public void $3Action(ActionEvent event) {
        requestShowGlobalInformation();
    }

    private void requestShowGlobalInformation() {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/windows/requestwindows/requestshowglobalinformation.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowGlobalInformationController showGlobalInformationController = (ShowGlobalInformationController) fxmlLoader1.getController();

        BorderPane borderPane = (BorderPane) youTubeLink.getParent().getScene().getRoot();
        AnchorPane field = (AnchorPane) borderPane.getCenter();
        field.getChildren().clear();
        field.setBottomAnchor(root, 10.0);
        field.setRightAnchor(root, 10.0);
        field.setTopAnchor(root, 10.0);
        field.setLeftAnchor(root, 10.0);
        field.getChildren().addAll(root);

    }

    private void showMainWindow(ActionEvent actionEvent) {
        BorderPane borderPane = (BorderPane)youTubeLink.getParent().getParent().getScene().getRoot();
        AnchorPane field = (AnchorPane) borderPane.getCenter();
        field.getChildren().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
