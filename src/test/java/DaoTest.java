import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rzzh.jrss.dao.RSSDao;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.utils.RSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by raozhanghui on 14/12/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DaoTest extends AbstractJUnit4SpringContextTests{
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
    public void testInsert(){
        List<Item> items = RSSClient.getRssItems("http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss");
        for(Item item:items){
            rssDao.addItem(item);
        }
    }
}
