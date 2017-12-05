package sample.firstrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestItems {
    ArrayList<ChannelResponse> items;

    public RequestItems() {
    }

    public ArrayList<ChannelResponse> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChannelResponse> items) {
        this.items = items;
    }
}
