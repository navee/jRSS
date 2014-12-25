import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.rssbean.RSS;
import org.rzzh.jrss.rssbean.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by raozhanghui on 14/12/23.
 */
public class XMLParseTest {
    public static void main(String[] args){
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet("http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss");
        try {
            HttpResponse response = client.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            XMLParseTest parse = new XMLParseTest();

            String xml = EntityUtils.toString(entity);
            EntityUtils.consume(entity);

            System.out.println(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(RSS.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader stringReader = new StringReader(xml);
            RSS rss = (RSS) unmarshaller.unmarshal(stringReader);
            System.out.println(rss.getChannel().getTitle());
            for(Item item: rss.getChannel().getItem()){
                System.out.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public String getuserxml(){
        User user = new User("navee",24);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(user,stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }

    public User parseUser(String xml){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader stringReader = new StringReader(xml);
            User user = (User) unmarshaller.unmarshal(stringReader);
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
