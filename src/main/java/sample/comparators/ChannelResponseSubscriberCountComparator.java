package sample.comparators;

import sample.firstrequest.ChannelResponse;

import java.util.Comparator;

public class ChannelResponseSubscriberCountComparator implements Comparator<ChannelResponse> {
    @Override
    public int compare(ChannelResponse o1, ChannelResponse o2) {
        return o1.getStatistics().getSubscriberCount().compareTo(o2.getStatistics().getSubscriberCount());
    }
}
