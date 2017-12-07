package sample.firstrequest.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet implements Serializable{
    private String title;
    private Date publishedAt;

    public Snippet() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titel) {
        this.title = titel;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snippet snippet = (Snippet) o;

        if (!title.equals(snippet.title)) return false;
        return publishedAt.equals(snippet.publishedAt);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + publishedAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "titel='" + title + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
