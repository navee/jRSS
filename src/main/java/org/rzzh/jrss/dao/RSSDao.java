package org.rzzh.jrss.dao;

import org.rzzh.jrss.entity.ChannelPO;
import org.rzzh.jrss.entity.ItemPO;
import org.rzzh.jrss.rssbean.Channel;
import org.rzzh.jrss.rssbean.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by raozhanghui on 14/12/24.
 */
@Repository
public class RSSDao extends BaseDao{
    public void addItem(ItemPO item){
        String sql = "insert into item (channel_id,title,link,description,pub_date,sync_date) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,item.getChannelId(),
                                item.getTitle(),
                                item.getLink(),
                                item.getDescription(),
                                item.getPubDate(),
                                item.getSyncDate());
    }

    public void addChannel(ChannelPO channel){
        String sql = "insert into channel (title,link,description,source_link) values (?,?,?,?)";
        jdbcTemplate.update(sql,channel.getTitle(),channel.getLink(),channel.getDescription(),channel.getSourceLink());
    }

    public List<ChannelPO> getAllChannel(){
        String sql = "select * from channel ";
        return jdbcTemplate.query(sql,new ChannelPORowMap());
    }

    public boolean checkExistItem(String link){
        String sql = "select count(*) from item where link = ?";
        int count = jdbcTemplate.queryForInt(sql,link);
        return count > 0 ? true : false;
    }

    public class ChannelPORowMap implements RowMapper<ChannelPO>{
        @Override
        public ChannelPO mapRow(ResultSet resultSet, int i) throws SQLException {
            ChannelPO channel = new ChannelPO();
            channel.setId(resultSet.getLong("id"));
            channel.setTitle(resultSet.getString("title"));
            channel.setLink(resultSet.getString("link"));
            channel.setDescription(resultSet.getString("description"));
            channel.setSourceLink(resultSet.getString("source_link"));
            return channel;
        }
    }
}
