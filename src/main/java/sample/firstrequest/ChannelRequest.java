package sample.firstrequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class ChannelRequest {
    private static final String YOU_TUBE = "https://www.googleapis.com/youtube/v3/{method}";
    private static final String API_KEY = "AIzaSyAlDUNpRHedwzs5pZoVQW1b4GExYfZg9bM";
    //private static final String videoId = "wWA7QawpNwU";


    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static RequestItems getChannelResponse(String channelId) throws IOException, UnirestException {
        HttpResponse<RequestItems> responses = Unirest.get(YOU_TUBE)
                .routeParam("method", "channels")
                .queryString("id", channelId)
                .queryString("part", "snippet,statistics")
                .queryString("key", API_KEY)
                .asObject(RequestItems.class);

        return responses.getBody();
    }
}
