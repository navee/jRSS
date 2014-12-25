import org.junit.Test;
import org.rzzh.jrss.service.RSSService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by raozhanghui on 14/12/25.
 */
public class ServiceTest extends SpringTestBase{
    @Autowired
    private RSSService rssService;

    @Test
    public void testSyncItem(){
        rssService.syncItem();
    }
}
