package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FindRidDao;
import cn.itcast.travel.domain.Rid;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 15:05$
 * @Version: 1.0
 */

public class FindRidDaoImpl implements FindRidDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据uid来获取所有的rid
     *
     * @param uid
     * @return
     */
    @Override
    public List<Rid> findByUid(int uid) {
        //定义sql语句
        String sql = " select rid from tab_favorite where uid = ?";
        System.out.println(1);
        List<Rid> rids = template.query(sql, new BeanPropertyRowMapper<Rid>(Rid.class), uid);

        return rids;
    }

    @Override
    public List<Route> findByList(List list ,int start, int pageSize) {
        int  s = list.size();
        List<Route> routes = null;
        try {
            if (list==null||list.size()<1){
                return null;
            }
//            System.out.println(list);
//            routes = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {

                if (i==list.size()-1){
                    sb.append(""+list.get(i)+"");
                }else {

                    sb.append("" + list.get(i) + ",");

                }
            }
            //定义sql语句
            String sql = "select * from tab_route where rid IN ("+sb.toString()+") limit "+ start +" ,"+pageSize+" ";
//            System.out.println(sql);

             routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
        } catch (DataAccessException e) {
            return null;
        }

        return routes;
    }

    /**
     * 获取总收藏数
     * @param uid
     * @return
     */
    @Override
    public int findTotalCount(int uid) {
        //定义sql语句
        String sql= " SELECT COUNT(*) FROM tab_favorite WHERE uid= ?";
        Integer count = template.queryForObject(sql, Integer.class, uid);
        return count;
    }

    /**
     * 获取表中所有的数据
     * @return
     * @param rname
     * @param smallMoney
     * @param bigMoney
     */
    @Override
    public int findEvery(String rname, int smallMoney, int bigMoney) {
        String sql = "SELECT COUNT(*) FROM tab_route where price>=  ? ";
        //定义sql语句
        List list = new ArrayList();
        list.add(smallMoney);
        StringBuilder sb = new StringBuilder(sql);
        if (rname!=null&&!"null".equals(rname)&&rname.length()>0&&!"undefined".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        if (bigMoney!=0){
            sb.append(" and price<= ? ");
            list.add(bigMoney);
        }
        System.out.println(list);
        System.out.println(sb.toString());
        //定义 sql语句

        int totalCount = template.queryForObject(sb.toString(), Integer.class,list.toArray());
        return totalCount;
    }

    /**
     * 获取Route 对象的集合
     * 并且分页进行获取(根据Count来进行排序)
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findFavoriteRank(int start, int pageSize,String rname,int smallMoney,int bigMoney) {
        //定义sql语句
//        String sql = "SELECT * FROM tab_route ORDER BY COUNT DESC LIMIT  ? , ? ";
        // String sql = "SELECT * FROM tab_route
        //定义sql语句
        String sql = "SELECT * FROM tab_route where price>=  ? ";
        //定义sql语句
        List list = new ArrayList();
        list.add(smallMoney);
        StringBuilder sb = new StringBuilder(sql);
        if (rname!=null&&!"null".equals(rname)&&rname.length()>0&&!"undefined".equals(rname)){
            sb.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        if (bigMoney!=0){
            sb.append(" and price<= ? ");
            list.add(bigMoney);
        }
        list.add(start);
        list.add(pageSize);
        sb.append(" ORDER BY COUNT DESC LIMIT  ? , ? ");
        System.out.println(sb.toString());
        System.out.println(list);
        List<Route> route = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), list.toArray());
        return route;
    }
}
