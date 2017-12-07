package sample.firstrequest.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics implements Serializable{
    private Long subscriberCount;
    private Long videoCount;
    private Long viewCount;

    public Statistics() {
    }

    public Long getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(Long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public Long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Long videoCount) {
        this.videoCount = videoCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        if (!subscriberCount.equals(that.subscriberCount)) return false;
        if (!videoCount.equals(that.videoCount)) return false;
        return viewCount.equals(that.viewCount);
    }

    @Override
    public int hashCode() {
        int result = subscriberCount.hashCode();
        result = 31 * result + videoCount.hashCode();
        result = 31 * result + viewCount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "subscriberCount=" + subscriberCount +
                ", videoCount=" + videoCount +
                ", viewCount=" + viewCount +
                '}';
    }
}
