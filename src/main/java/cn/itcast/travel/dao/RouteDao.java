package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 18:45$
 * @Version: 1.0
 */
//路线dao层
public interface RouteDao {
    /**
     * 根据cid来查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid 和起始的数以及每页的条数来查询路线集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据rid来查询线路详情信息
     * @param rid
     * @return
     */
    Route findOne(int rid);

}
