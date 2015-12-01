import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rzzh.jrss.dao.RSSDao;
import org.rzzh.jrss.entity.ChannelPO;
import org.rzzh.jrss.entity.ItemPO;
import org.rzzh.jrss.rssbean.Channel;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.utils.RSSClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by raozhanghui on 14/12/24.
 */
public class DaoTest extends SpringTestBase{
    @Autowired
    private RSSDao rssDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        long count = jdbcTemplate.queryForLong("select count(*) from t_user");
        Assert.assertEquals(count,1L);
    }

    @Test
    public void testAddItems(){
        List<Item> items = RSSClient.getRssItems("http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss");
        for(Item item:items){
            ItemPO itemPO = new ItemPO();
            BeanUtils.copyProperties(item,itemPO);
            itemPO.setChannelId(1L);
            rssDao.addItem(itemPO);
        }
    }

    @Test
    public void testAddChannel(){
        String rssSourceLink = "http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss";
        Channel channel = RSSClient.getChannel(rssSourceLink);
        ChannelPO channelPO = new ChannelPO();
        BeanUtils.copyProperties(channel,channelPO);
        channelPO.setSourceLink(rssSourceLink);
        rssDao.addChannel(channelPO);
    }

    @Test
    public void asyncChannelItem(){
        List<ChannelPO> channels = rssDao.getAllChannel();
        for(ChannelPO channel : channels){
            String sourceLink = channel.getSourceLink();
            List<Item> items = RSSClient.getRssItems(sourceLink);
            for(Item item : items){
                if(!rssDao.checkExistItem(item.getLink())){
                    ItemPO itemPO = new ItemPO();
                    BeanUtils.copyProperties(item,itemPO);
                    rssDao.addItem(itemPO);
                }
            }
        }
    }
}
