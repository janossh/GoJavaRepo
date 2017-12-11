package sample.comparators;

import sample.firstrequest.ChannelResponse;

import java.util.Comparator;

public class ChannelResponseDateComparator implements Comparator<ChannelResponse> {
    @Override
    public int compare(ChannelResponse o1, ChannelResponse o2) {
        return o1.getSnippet().getPublishedAt().compareTo(o2.getSnippet().getPublishedAt());
    }
}
