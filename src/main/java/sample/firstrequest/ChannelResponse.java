package sample.firstrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sample.firstrequest.jsonObjects.Snippet;
import sample.firstrequest.jsonObjects.Statistics;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelResponse implements Serializable{
    private String id;
    private Snippet snippet;
    private Statistics statistics;

    public ChannelResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChannelResponse that = (ChannelResponse) o;

        if (!id.equals(that.id)) return false;
        if (!snippet.equals(that.snippet)) return false;
        return statistics.equals(that.statistics);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + snippet.hashCode();
        result = 31 * result + statistics.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ChannelResponse{" +
                "id='" + id + '\'' +
                ", snippet=" + snippet +
                ", statistics=" + statistics +
                '}';
    }
}
