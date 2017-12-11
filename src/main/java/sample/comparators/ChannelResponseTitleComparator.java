package sample.comparators;

import sample.firstrequest.ChannelResponse;

import java.util.Comparator;

public class ChannelResponseTitleComparator implements Comparator<ChannelResponse>{
    @Override
    public int compare(ChannelResponse o1, ChannelResponse o2) {
        return o1.getSnippet().getTitle().compareTo(o2.getSnippet().getTitle());
    }
}
