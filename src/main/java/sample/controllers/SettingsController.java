package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.Settings;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {


    @FXML
    private TextField useCache;
    @FXML
    private TextField cachePath;
    @FXML
    private Label labelCurentCachePath;
    @FXML
    private TextField showLeadTime;

    @FXML
    private Button saveSettingsButton;

    @FXML
    private Button canselButton;

    public Label getLabelCurentCachePath() {
        return labelCurentCachePath;
    }

    public TextField getUseCache() {
        return useCache;
    }

    public void setUseCache(TextField useCache) {
        this.useCache = useCache;
    }

    public TextField getCachePath() {
        return cachePath;
    }

    public void setCachePath(TextField cachePath) {
        this.cachePath = cachePath;
    }

    public TextField getShowLeadTime() {
        return showLeadTime;
    }

    public void setShowLeadTime(TextField showLeadTime) {
        this.showLeadTime = showLeadTime;
    }

    @FXML
    public void saveSettingsAction(ActionEvent event) throws Exception {
        File path = new File(cachePath.getText());
        if ((path.exists()&&path.isDirectory())&&(showLeadTime.getText().compareToIgnoreCase("true") == 0 || showLeadTime.getText().compareToIgnoreCase("false") == 0) &&
                (useCache.getText().compareToIgnoreCase("true") == 0 || useCache.getText().compareToIgnoreCase("false") == 0)) {
            Settings.setCachePath(path.getPath()+"/");
            Settings.setShowLeadTime(showLeadTime.getText().compareToIgnoreCase("true") == 0);
            Settings.setUseCache(useCache.getText().compareToIgnoreCase("true") == 0);
            Settings.saveSettings();
            showMainWindow(event);
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Check values. Cache path or enter \"TRUE\" or \"FALSE\"");

            alert.showAndWait();

        }
    }

    @FXML
    public void canselAction(ActionEvent event) throws Exception {
        showMainWindow(event);
    }


    private void showMainWindow(ActionEvent actionEvent) {
        BorderPane borderPane = (BorderPane) ((Node) actionEvent.getSource()).getParent().getParent().getScene().getRoot();
        AnchorPane field = (AnchorPane) borderPane.getCenter();
        field.getChildren().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
