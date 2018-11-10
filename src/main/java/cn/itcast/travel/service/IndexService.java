package cn.itcast.travel.service;

import cn.itcast.travel.domain.RAndRimg;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/15$ 11:46$
 * @Version: 1.0
 */
public interface IndexService {
    /**
     * 查询前几个人气旅游
     * @param count
     * @return
     */
    //获取人气旅游的方法
    List<Route>  route1(int count);

    /**
     * 获取最新旅游路线
     * @param count
     * @return
     */
    List<Route> route2(int count);

    /**
     * 查询主题路线
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

    /**
     * 每页的推荐
     * @param count
     * @return
     */
    List<RAndRimg> route6(int count);
}
