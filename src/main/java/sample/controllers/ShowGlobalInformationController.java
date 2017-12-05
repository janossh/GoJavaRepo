package sample.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Settings;
import sample.firstrequest.ChannelRequest;
import sample.firstrequest.ChannelResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ShowGlobalInformationController {


    @FXML
    private TextField channelIdsString;

    @FXML
    private TextArea resultString;


    @FXML
    public void buttonCanselAction() {
        channelIdsString.setText("");
        resultString.setText("");
    }

    @FXML
    public void buttonGoAction() {

        String[] channelIds = channelIdsString.getText().split(",");
        ArrayList<String> ids = new ArrayList<String>();
        for (String id : channelIds) {
            if (id != null && !id.isEmpty())
                ids.add(id);
        }
        if (ids.size() == 1) {
            ArrayList<ChannelResponse> channelResponses = new ArrayList<ChannelResponse>();
            Date start = new Date();
            try {

                ArrayList<ChannelResponse> items = ChannelRequest.getChannelResponse(channelIdsString.getText()).getItems();

                for (ChannelResponse request : items) {

                    channelResponses.add(request);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            Date end = new Date();
            if (channelResponses.size() == 1) {

                String result = //channelResponses.get(0).getId() + System.lineSeparator() +
                                "1. " + channelResponses.get(0).getSnippet().getTitle() + System.lineSeparator() +
                                "2. " + channelResponses.get(0).getSnippet().getPublishedAt() + System.lineSeparator() +
                                "3. " + channelResponses.get(0).getStatistics().getSubscriberCount() + System.lineSeparator() +
                                "4. " + channelResponses.get(0).getStatistics().getVideoCount() + System.lineSeparator() +
                                "5. " + channelResponses.get(0).getStatistics().getViewCount();
                if(Settings.isShowLeadTime()){
                    result += System.lineSeparator() + "6. LeadTime -- "+((double)(end.getTime() - start.getTime())/1000)+ " second(s)";
                }
                resultString.setText(result);
            }

        }

    }
}
