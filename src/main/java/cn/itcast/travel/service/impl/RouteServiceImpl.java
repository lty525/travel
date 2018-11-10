package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 18:25$
 * @Version: 1.0
 */

public class RouteServiceImpl implements RouteService {
   private RouteDao dao = new RouteDaoImpl();
   private RouteImageDao routeImageDao = new RouteImageDaoImpl();
   private SellerDao sellerDao = new SellerDaoImpl();
   private FavoriteCountDao favoriteCountDao =new FavoriteCountDaoImpl();
   private FindRidDao findRidDao = new FindRidDaoImpl();
   private FindFavoritePb findFavoritePb = new FindFavoritePbImpl();
    /**
     * 封装PageBean对象
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //给PageBean赋值
        PageBean<Route> pb  = new PageBean<Route>();
        pb.setCurrentPage(currentPage);//当前页码
        pb.setPageSize(pageSize);//每页显示的条数
        //获取总记录数
        int totalCount = dao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);//总记录数
        //计算开始数
        int start = (currentPage-1)*pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize ,rname);
        pb.setList(list);//页面显示的集合
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(int rid) {

        //根据rid来获取Route对象
        Route route = dao.findOne(rid);
        //根据rid来获取图片信息集合
        List<RouteImg> routeImg = routeImageDao.findRouteImg(rid);
        route.setRouteImgList(routeImg);
        //根据sid来查询商家的信息
        Seller seller = sellerDao.findSeller(route.getSid());
        route.setSeller(seller);
//        System.out.println(route);
        int count = favoriteCountDao.findFavoriteCountByrid(rid);
        route.setCount(count);

        return route;
    }

    /**
     * 根据uid来获取一个List<Route>的集合
     * @param uid
     * @return
     */

    @Override
    public List<Route> findMyFavorite(int uid, int start, int pageSize) {
        //根据uid来获取rid
        List<Rid> rid = findRidDao.findByUid(uid);
        //通过list集合来获取List<Route>的集合
        List list = new ArrayList();
        for (int i = 0; i < rid.size(); i++) {
            list.add(rid.get(i).getRid());
        }
 List<Route> routes =  findRidDao.findByList(list,start,  pageSize);

        return routes;
    }

    @Override
    public PageBean<Route> findMyFavoritePageBean(int uid, int currentPage, int pageSize) {
        //给PageBean赋值
        PageBean<Route> pb  = new PageBean<Route>();
        pb.setCurrentPage(currentPage);//当前页码
        pb.setPageSize(pageSize);//每页显示的条数
        //获取总记录数
        int totalCount = findRidDao.findTotalCount(uid);
        pb.setTotalCount(totalCount);//总记录数
        //计算开始数
        int start = (currentPage-1)*pageSize;
        List<Route> list = findMyFavorite(uid, start, pageSize );
        pb.setList(list);//页面显示的集合
        int totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<Route> findMyFavoriteRank(int currentPage, int pageSize,String rname,int smallMoney,int bigMoney) {
        PageBean<Route> pb  = new PageBean<Route>();
        pb.setCurrentPage(currentPage);//当前页码
        pb.setPageSize(pageSize);//每页显示的条数
        //获取表中所有的数量
        int totalCount = findRidDao.findEvery(rname,smallMoney,bigMoney);
        pb.setTotalCount(totalCount);//总记录数

        int start = (currentPage-1)*pageSize;
        //获取所有的
        List<Route> list = findRidDao.findFavoriteRank(start, pageSize,rname,smallMoney,bigMoney);
        pb.setList(list);


        int totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
