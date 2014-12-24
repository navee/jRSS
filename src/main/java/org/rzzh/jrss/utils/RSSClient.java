package org.rzzh.jrss.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.rssbean.RSS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by raozhanghui on 14/12/24.
 */
public class RSSClient {
    private static CloseableHttpClient client = HttpClients.createDefault();

    public static List<Item> getRssItems(String url){
        String rssXml = requestRssXml(url);
        System.out.println(rssXml);
        List<Item> items = parestXml2Item(rssXml);
        return items;
    }

    private static String requestRssXml(String url){
        HttpGet request = new HttpGet("http://cnbeta.feedsportal.com/c/34306/f/624776/index.rss");
        HttpResponse response = null;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String xml = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return xml;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.releaseConnection();
        }
        return "";
    }

    private static List<Item> parestXml2Item(String xml){
        JAXBContext jaxbContext = null;
        List<Item> items = new ArrayList<Item>();
        try {
            jaxbContext = JAXBContext.newInstance(RSS.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader stringReader = new StringReader(xml);
            RSS rss = (RSS) unmarshaller.unmarshal(stringReader);
            items = rss.getChannel().getItem();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return items;
    }
}
