package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 10:13$
 * @Version: 1.0
 */

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 通过rid和uid来查询用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite findByUidAndRid(int rid, int uid) {

        Favorite favorite = null;
        try {
            System.out.println(uid+":"+rid);
            String sql ="select * from tab_favorite where rid = ? and uid = ?  ";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    /**
     * 添加收藏
     * @param i
     * @param uid
     */
    @Override

    public void add(int i, int uid) {

        //定义sql语句
        String sql = " insert into tab_favorite (rid,date,uid) values(?,?,?)";
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());

        template.update(sql,i,date,uid);

    }

    /**
     * 取消收藏
     * @param rid
     * @param uid
     */
    @Override
    public void cancelFavorite(int rid, int uid) {
        //定义sql语句
        String sql = "DELETE FROM tab_favorite WHERE rid = ? AND uid= ? ";
        template.update(sql,rid,uid);
    }
}
