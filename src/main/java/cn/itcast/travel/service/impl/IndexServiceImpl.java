package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IndexDao;
import cn.itcast.travel.dao.impl.IndexDaoImpl;
import cn.itcast.travel.domain.RAndRimg;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IndexService;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/15$ 11:48$
 * @Version: 1.0
 */

public class IndexServiceImpl  implements IndexService {
    IndexDao indexDao = new IndexDaoImpl();
    /**
     * 传递要查询的数量然后插叙收藏最多的四个
     * @param count
     * @return
     */
    @Override
    public List<Route> route1(int count) {
        List<Route> routes = indexDao.route1(count);
        return routes;
    }

    /**
     * 最新旅游路线
     * @param count
     * @return
     */
    @Override
    public List<Route> route2(int count) {
        return indexDao.route2(count);
    }

    /**
     * 主题游
     * @param count
     * @return
     */
    @Override
    public List<Route> route3(int count) {
        return indexDao.route3(count);
    }

    /**
     * 国内游
     * @param count
     * @return
     */
    @Override
    public List<Route> route4(int count) {
        return indexDao.route4(count);
    }

    /**
     * 境外游
     * @param count
     * @return
     */
    @Override
    public List<Route> route5(int count) {
        return indexDao.route5(count);
    }

    /**
     * 每页的推荐
     *
     * @param count
     * @return
     */
    @Override
    public List<RAndRimg> route6(int count) {
        //
        return indexDao.route6(count);
    }
}
