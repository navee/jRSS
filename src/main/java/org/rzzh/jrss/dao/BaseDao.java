package org.rzzh.jrss.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by raozhanghui on 14/12/24.
 */
public abstract class BaseDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

}
