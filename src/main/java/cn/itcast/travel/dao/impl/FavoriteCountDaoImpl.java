package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteCountDao;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 11:27$
 * @Version: 1.0
 */

public class FavoriteCountDaoImpl implements FavoriteCountDao {
    private JdbcTemplate template  = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据rid来查询次数
     * @param rid
     * @return
     */
    @Override
    public int findFavoriteCountByrid(int rid) {
        //定义sql语句
        String sql = " select count(*) from tab_favorite where rid = ?";
        int count = template.queryForObject(sql,Integer.class,rid);
        //设置数据库route的count
        String sql1 = "update tab_route set count = ? where rid = ?";
        template.update(sql1,count,rid);
        return count;
    }
}
