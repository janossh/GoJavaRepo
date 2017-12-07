package sample.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Settings;
import sample.firstrequest.ChannelRequest;
import sample.firstrequest.ChannelResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ShowGlobalInformationController {


    @FXML
    private TextField channelIdsString;

    @FXML
    private TextArea resultString;
    @FXML
    private TextArea resultString2;


    @FXML
    public void buttonCanselAction() {
        channelIdsString.setText("");
        resultString.setText("");
        resultString2.setVisible(false);
        resultString2.setText("");
    }

    @FXML
    public void buttonGoAction() {

        String[] channelIds = channelIdsString.getText().split(",");
        ArrayList<String> ids = new ArrayList<String>();
        for (String id : channelIds) {
            if (id != null && !id.isEmpty())
                ids.add(id.trim());
        }

        ArrayList<ChannelResponse> channelResponses = new ArrayList<ChannelResponse>();

        String idsString = "";
        for (int i = 0; i < ids.size(); i++) {
            if (Settings.isUseCache()) {
                if (checkFileExist(ids.get(i))) {
                    ChannelResponse resp = readObjectFromFile(ids.get(i));
                    if (resp != null) {
                        channelResponses.add(resp);
                        continue;
                    }
                }
            }
            idsString += ids.get(i) + (i != ids.size() - 1 ? "," : "");
        }

        Date start = new Date();

        try {
            if (!idsString.isEmpty()) {

                ArrayList<ChannelResponse> items = ChannelRequest.getChannelResponse(idsString).getItems();

                for (ChannelResponse request : items) {

                    channelResponses.add(request);
                    if (Settings.isUseCache()) {
                        saveObjectToFile(request.getId(), request);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        Date end = new Date();

        if (ids.size() == 1 || ids.size() == 2) {

            if (channelResponses.size() == 1 || channelResponses.size() == 2) {

                String result = perfomResult(channelResponses, 0);
                //channelResponses.get(0).getId() + System.lineSeparator() +

                if (Settings.isShowLeadTime()) {
                    result += System.lineSeparator() + "6. LeadTime -- " + ((double) (end.getTime() - start.getTime()) / 1000) + " second(s)";
                }
                resultString.setText(result);
                resultString2.setVisible(false);
            }

            if (channelResponses.size() == 2) {

                String result = perfomResult(channelResponses, 1);
                //channelResponses.get(0).getId() + System.lineSeparator() +

                if (Settings.isShowLeadTime()) {
                    result += System.lineSeparator() + "6. LeadTime -- " + ((double) (end.getTime() - start.getTime()) / 1000) + " second(s)";
                }
                resultString2.setText(result);
                resultString2.setVisible(true);
            }

        }

    }

    private String perfomResult(ArrayList<ChannelResponse> channelResponses, int index) {
        return "1. " + channelResponses.get(index).getSnippet().getTitle() + System.lineSeparator() +
                "2. " + channelResponses.get(index).getSnippet().getPublishedAt() + System.lineSeparator() +
                "3. " + channelResponses.get(index).getStatistics().getSubscriberCount() + System.lineSeparator() +
                "4. " + channelResponses.get(index).getStatistics().getVideoCount() + System.lineSeparator() +
                "5. " + channelResponses.get(index).getStatistics().getViewCount();
    }

    private void saveObjectToFile(String fileName, ChannelResponse obj) {
        try {
            FileOutputStream fo = new FileOutputStream(Settings.getCachePath() + fileName + ".dat");
            ObjectOutputStream serial = new ObjectOutputStream(fo);
            serial.writeObject(obj);
            serial.flush();
            fo.close();
            serial.close();
        } catch (Exception ex) {

            showAlert();
            ex.printStackTrace();
        }
    }

    private ChannelResponse readObjectFromFile(String fileName) {

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(Settings.getCachePath() + fileName + ".dat");
        } catch (FileNotFoundException e) {
            showAlert();
            e.printStackTrace();
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fin);
        } catch (IOException e) {
            showAlert();
            e.printStackTrace();
        }

        try {
            return (ChannelResponse) ois.readObject();
        } catch (IOException e) {
            showAlert();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            showAlert();
            e.printStackTrace();
        }
        return null;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Error with save/read cache to/from " + Settings.getCachePath());

        alert.showAndWait();
    }

    private boolean checkFileExist(String fileName) {
        File file = new File(Settings.getCachePath() + fileName + ".dat");
        return file.exists();
    }

}
