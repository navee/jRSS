import org.junit.Test;
import org.rzzh.jrss.entity.ItemPO;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.utils.RSSClient;

import java.util.List;

/**
 * Created by raozhanghui on 14/12/26.
 */
public class RSSClientTest {
    @Test
    public void testGetItems(){
        List<Item> items = RSSClient.getRssItems("http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss");
        for(Item item:items){
            System.out.println(item);
        }
    }
}
