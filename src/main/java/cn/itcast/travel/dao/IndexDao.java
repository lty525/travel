package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RAndRimg;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/15$ 11:51$
 * @Version: 1.0
 */
public interface IndexDao {
    /**
     * 查询前几个人气旅游
     * @param count
     * @return
     */
    //获取人气旅游的方法
    List<Route> route1(int count);

    /**
     * 查询最新旅游
     * @param count
     * @return
     */
    List<Route> route2(int count);

    /**
     * 主题游
     * @param count
     * @return
     */
    List<Route> route3(int count);

    /**
     * 国内游
     * @param count
     * @return
     */
    List<Route> route4(int count);

    /**
     * 境外游
     * @param count
     * @return
     */

    List<Route> route5(int count);

    List<RAndRimg> route6(int count);

}
