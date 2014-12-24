package org.rzzh.jrss.dao;

import org.rzzh.jrss.rssbean.Item;
import org.springframework.stereotype.Repository;

/**
 * Created by raozhanghui on 14/12/24.
 */
@Repository
public class RSSDao extends BaseDao{
    public void addItem(Item item){
        String sql = "insert into item (title,link,description) values (?,?,?)";
        jdbcTemplate.update(sql,item.getTitle(),item.getLink(),item.getDescription());
    }
}
