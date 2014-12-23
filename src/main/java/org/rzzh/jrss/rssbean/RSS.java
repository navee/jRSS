package org.rzzh.jrss.rssbean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by raozhanghui on 14/12/23.
 */
@XmlRootElement
public class RSS {
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
