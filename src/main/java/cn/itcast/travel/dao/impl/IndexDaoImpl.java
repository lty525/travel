package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.IndexDao;
import cn.itcast.travel.domain.RAndRimg;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/15$ 11:51$
 * @Version: 1.0
 */

public class IndexDaoImpl implements IndexDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 获取人气旅游
     * @param count
     * @return
     */
    @Override
    public List<Route> route1(int count) {
        //定义sql语句
        String sql = " SELECT * FROM tab_route ORDER BY  COUNT  DESC ";
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i<count){
                routes.add(route.get(i));
            }
        }
        return routes;
    }

    /**
     * 查询最新旅游路线
     * @param count
     * @return
     */
    @Override
    public List<Route> route2(int count) {
        //定义sql语句
        String sql = " SELECT * FROM tab_route ORDER BY  rdate  DESC ";
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i<count){
                routes.add(route.get(i));
            }
        }
        return routes;
    }

    @Override
    public List<Route> route3(int count) {
        //定义sql语句
        String sql = " SELECT * FROM tab_route WHERE isThemeTour= 1 ORDER BY  rdate DESC";
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i<count){
                routes.add(route.get(i));
            }
        }
        return routes;
    }

    /**
     * 国内游
     * @param count
     * @return
     */
    @Override
    public List<Route> route4(int count) {
        //定义sql语句
        String sql = " SELECT * FROM tab_route WHERE RNAME like ? ORDER BY  rdate DESC";
        String pa = "%"+"黄山"+"%";
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),pa);
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i<count){
                routes.add(route.get(i));
            }
        }
        return routes;
    }

    /**
     * 境外游
     *
     * @param count
     * @return
     */
    @Override
    public List<Route> route5(int count) {
        //定义sql语句
        String sql = " SELECT * FROM tab_route WHERE RNAME like ? ORDER BY  rdate DESC";
        String pa = "%"+"海南"+"%";
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),pa);
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            if (i<count){
                routes.add(route.get(i));
            }
        }
        return routes;
    }

    @Override
    public List<RAndRimg> route6(int count) {
        //定义sql语句
        String sql = "SELECT * FROM tab_route ,tab_route_img  WHERE tab_route.rid=tab_route_img.rid ORDER BY tab_route.COUNT DESC;";

        List<RAndRimg> rAndRimgs = template.query(sql, new BeanPropertyRowMapper<RAndRimg>(RAndRimg.class));
        List<RAndRimg> routes = new ArrayList<>();
        for (int i = 0; i < rAndRimgs.size(); i++) {
           if(rAndRimgs.get(i).getRid()!=rAndRimgs.get(i+1).getRid()){
               routes.add(rAndRimgs.get(i));
               if (routes.size()>=5){
                   return routes;
               }
           }
        }
        return routes;
    }


}
