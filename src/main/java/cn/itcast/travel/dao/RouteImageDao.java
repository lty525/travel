package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/11$ 16:37$
 * @Version: 1.0
 */


public interface RouteImageDao {
    /**
     * 通过rid来获取图片的集合
     * @param rid
     * @return
     */
    public List<RouteImg> findRouteImg(int rid);
}
