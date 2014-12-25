package org.rzzh.jrss.dao;

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
    public void addItem(Item item){
        String sql = "insert into item (title,link,description) values (?,?,?)";
        jdbcTemplate.update(sql,item.getTitle(),item.getLink(),item.getDescription());
    }

    public void addChannel(Channel channel){
        String sql = "insert into channel (title,link,description,source_link) values (?,?,?,?)";
        jdbcTemplate.update(sql,channel.getTitle(),channel.getLink(),channel.getDescription(),channel.getSourceLink());
    }

    public List<Channel> getAllChannel(){
        String sql = "select * from channel ";
        return jdbcTemplate.query(sql,new ChannelRowMap());
    }

    public boolean checkExistItem(String link){
        String sql = "select count(*) from item where link = ?";
        int count = jdbcTemplate.queryForInt(sql,link);
        return count > 0 ? true : false;
    }

    public class ChannelRowMap implements RowMapper<Channel>{
        @Override
        public Channel mapRow(ResultSet resultSet, int i) throws SQLException {
            Channel channel = new Channel();
            channel.setTitle(resultSet.getString("title"));
            channel.setLink(resultSet.getString("link"));
            channel.setDescription(resultSet.getString("description"));
            channel.setSourceLink(resultSet.getString("source_link"));
            return channel;
        }
    }
}
