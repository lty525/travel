package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 18:19$
 * @Version: 1.0
 */

/**
 * 路线服务
 */
public interface RouteService {
    /**
     * 分页查询方法
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */

    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据rid来查询线路详情信息
     * @param rid
     * @return
     */
    Route findOne(int rid);


    /**
     * 根据rid和uid来获取一个 List<Route>集合

     * @param uid
     * @return
     */
    List<Route> findMyFavorite(int uid, int start, int pageSize);

    PageBean<Route> findMyFavoritePageBean(int uid, int currentPage, int pageSize);

    /**
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Route> findMyFavoriteRank(int currentPage, int pageSize,String rname,int smallMoney,int bigMoney);
}
