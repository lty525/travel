package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/11$ 16:47$
 * @Version: 1.0
 */

public class SellerDaoImpl  implements SellerDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据sid来获取商家的详情信息
     * @param sid
     * @return
     */
    @Override
    public Seller findSeller(int sid) {
        //定义sql语句
        String sql = " select * from tab_seller where sid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid);

    }
}
