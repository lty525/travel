package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Rid;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 15:04$
 * @Version: 1.0
 */
public interface FindRidDao {
    List<Rid> findByUid(int uid);

    /**
     * 通过rid来获取list<Route>集合
     * @param list
     * @return
     */

    List<Route> findByList(List list,int start, int pageSize);

    /**
     * 根据uid 来获取总数
     * @param uid
     * @return
     */
    int findTotalCount(int uid);

    /**
     * 获取表中所有的数据
     * @return
     * @param rname
     * @param smallMoney
     * @param bigMoney
     */
    int findEvery(String rname, int smallMoney, int bigMoney);

    /**
     * 获取所有的分页
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findFavoriteRank(int start, int pageSize,String rname,int smallMoney,int bigMoney);
}
