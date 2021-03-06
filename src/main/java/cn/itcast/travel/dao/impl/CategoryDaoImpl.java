package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 11:36$
 * @Version: 1.0
 */

public class CategoryDaoImpl implements CategoryDao {
    //创建JdbcTemplate工具
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        //定义sql语句
        String sql = "select * from tab_category";
        List<Category> categories = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return categories;
    }
}
