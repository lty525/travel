package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 18:49$
 * @Version: 1.0
 */

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据cid来查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        //定义查询语句
//        String sql = "select count(*) from tab_route where cid=?";
        //重新定义sql语句
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List p = new ArrayList();
        //拼接sql语句
        if (cid!=0){
            //如果cid有值
            sb.append("and cid = ? ");
            p.add(cid);
        }
        if (rname!=null&& rname.length()>0 ){
            //不为空且传递大于0
            sb.append(" and rname like ? ");
            p.add("%"+rname+"%");
        }
        sql=sb.toString();

        return template.queryForObject(sql,Integer.class,p.toArray());
    }

    /**
     * 根据cid 和起始的数以及每页的条数来查询路线集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //定义sql语句
//        String sql = " select * from tab_route where cid = ? limit ? , ?";
        String sql = " select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List p = new ArrayList();
        //拼接sql语句
        if (cid!=0){
            //如果cid有值
            sb.append("and cid = ? ");
            p.add(cid);
        }
        if (rname!=null&& rname.length()>0){
            //不为空且传递大于0
            sb.append(" and rname like ? ");
            p.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        p.add(start);
        p.add(pageSize);
        sql=sb.toString();
        List<Route> list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), p.toArray());
        return list;
    }

    /**
     * 根据rid来查询线路详情信息
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        //定义sql语句
        String sql = " select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
