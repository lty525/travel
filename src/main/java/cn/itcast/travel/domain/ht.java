package cn.itcast.travel.domain;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.FindRidDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.FindRidDaoImpl;
import cn.itcast.travel.service.IndexService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.IndexServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 12:38$
 * @Version: 1.0
 */


public class ht {
    public static void main(String[] args) {

        IndexService indexService = new IndexServiceImpl();
        List<RAndRimg> routes = indexService.route6(5);
        System.out.println(routes.size());
    }
}
