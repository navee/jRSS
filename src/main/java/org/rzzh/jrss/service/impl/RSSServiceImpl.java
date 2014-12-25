package org.rzzh.jrss.service.impl;

import org.rzzh.jrss.dao.RSSDao;
import org.rzzh.jrss.entity.ChannelPO;
import org.rzzh.jrss.entity.ItemPO;
import org.rzzh.jrss.rssbean.Item;
import org.rzzh.jrss.service.RSSService;
import org.rzzh.jrss.utils.RSSClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by raozhanghui on 14/12/25.
 */
@Service
public class RSSServiceImpl implements RSSService{
    @Autowired
    private RSSDao rssDao;

    @Override
    public void syncItem() {
        List<ChannelPO> channels = rssDao.getAllChannel();
        for(ChannelPO channel : channels){
            String sourceLink = channel.getSourceLink();
            List<Item> items = RSSClient.getRssItems(sourceLink);
            for(Item item : items){
                if(!rssDao.checkExistItem(item.getLink())){
                    ItemPO itemPO = new ItemPO();
                    BeanUtils.copyProperties(item, itemPO);
                    rssDao.addItem(itemPO);
                }
            }
        }
    }
}
