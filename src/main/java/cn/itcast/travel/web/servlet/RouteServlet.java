package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private UserService userService = new UserServiceImpl();

    /**
     * 分页查询的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPagestr = request.getParameter("currentPage");//当前页面
        String pageSizestr = request.getParameter("pageSize");//每页的条数
        String cidstr = request.getParameter("cid");//类别id
        String rname = request.getParameter("rname");
//        System.out.println(rname);
        //转码
//        System.out.println(rname);
        if ("null".equals(rname)) {
            rname = null;
        } else {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        }

        //进行判断 和转换
        int cid = 0;//旅游路线id
        if (cidstr != null && cidstr.length() > 0 && !"null".equals(cidstr)) {
            cid = Integer.parseInt(cidstr);
        }
        int currentPage = 0;//当前页面
        if (currentPagestr != null && currentPagestr.length() > 0) {
            currentPage = Integer.parseInt(currentPagestr);
        } else {
            //如果为空的话就设置默认值
            currentPage = 1;
        }
        int pageSize = 0;//当前页面
        if (pageSizestr != null && pageSizestr.length() > 0) {
            pageSize = Integer.parseInt(pageSizestr);
        } else {
            //如果为空的话就设置默认值
            pageSize = 5;
        }
        //调用Service方PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //将pb对象数列json 并返回到客户端
        writeValue(pb, response);
    }

    /**
     * 通过rid来获取一条线路的详情信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取rid
        String rids = request.getParameter("rid");
        int rid = 0;
        if (rids != null && rids.length() > 0 && !"null".equals(rids)) {
            rid = Integer.parseInt(rids);
        }
        //调用Service方法
        Route route = routeService.findOne(rid);
        //把route对象回写
        writeValue(route, response);
    }

    /**
     * 查询是否有收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取路线
        String rid = request.getParameter("rid");
        //获取是否有session值
        User user = (User) request.getSession().getAttribute("user");
        int uid;


        if (user == null ) {
            //说明用户没有登录
            return;
        } else {
            uid = user.getUid();

        }
        if (uid == 0) {


            User u = userService.login1(user.getUsername());


            uid = u.getUid();


        }
        //然后根据uid 和rid和获取
        boolean flag = favoriteService.findByUidAndRid(rid, uid);
        writeValue(flag, response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取路线
        String rid = request.getParameter("rid");
//        String uidStr = request.getParameter("uid");
        //获取是否有session值
        User user = (User) request.getSession().getAttribute("user");


//        System.out.println(user.getName());

        int uid;
        //判断用户是否登录
        if (user == null ) {
            //说明用户没有登录
            return;
        } else {
            uid = user.getUid();

        }
        ResultInfo info = new ResultInfo();

//            System.out.println(user.getUsername()+"add");
        if (uid == 0) {


            User u = userService.login1(user.getUsername());


                uid = u.getUid();


        }

        favoriteService.add(rid, uid);


        //调用Servic的add()方法把收藏添加进去
    }

    /**
     * 获取我的收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPagestr = request.getParameter("currentPage");//当前页面
        String pageSizestr = request.getParameter("pageSize");//每页的条数
        int currentPage = 0;//当前页面
        if (currentPagestr != null && currentPagestr.length() > 0) {
            currentPage = Integer.parseInt(currentPagestr);
        } else {
            //如果为空的话就设置默认值
            currentPage = 1;
        }
        int pageSize = 0;//当前页面
        if (pageSizestr != null && pageSizestr.length() > 0) {
            pageSize = Integer.parseInt(pageSizestr);
        } else {
            //如果为空的话就设置默认值
            pageSize = 8;
        }
        //获取是否有session值
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        //判断用户是否登录
        if (user == null ) {
            //说明用户没有登录
            return;
        } else {
            uid = user.getUid();

        }
        ResultInfo info = new ResultInfo();

        if (uid == 0) {

            System.out.println(user.getUsername());

            User u = userService.login1(user.getUsername());

            if (u != null) {
                uid = u.getUid();
//                System.out.println(uid+"myf");
            }
        }

        //根据uid和rid来获取list<Route集合>
//        List<Route> routes = routeService.findMyFavorite(uid);
        PageBean<Route> pb = routeService.findMyFavoritePageBean(uid, currentPage, pageSize);
//        ResultInfo info = new ResultInfo();
        if (pb == null || pb.getList() == null) {
            //说明此用户没有收藏
            info.setFlag(false);
            info.setErrorMsg("暂时还没有收藏,可以去看看");
            return;

        } else {
            //有收藏
            info.setFlag(true);
            info.setData(pb);
        }

        writeValue(info, response);


    }

    /**
     * 查询收藏排行榜
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void favoriteRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPagestr = request.getParameter("currentPage");//当前页面
        String pageSizestr = request.getParameter("pageSize");//每页的条数
        String rname = request.getParameter("rname");//搜索的内容
        String smallMoneyStr = request.getParameter("smallMoney");//起始钱数
        String bigMoneyStr = request.getParameter("bigMoney");//搜索的最大钱数
//        System.out.println(rname);
        if ("null".equals(rname) || rname == null || rname.length() < 0) {
//            rname = "天";
            rname = null;
        } else {

            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }

//         rname = URLDecoder.decode(rname,"utf-8");

        int smallMoney = 0;//最小值
        if (smallMoneyStr != null && smallMoneyStr.length() > 0) {
            smallMoney = Integer.parseInt(smallMoneyStr);
        } else {
            smallMoney = 0;

        }
        int bigMoney = 0;//最大值
        if (bigMoneyStr != null && bigMoneyStr.length() > 0) {
            bigMoney = Integer.parseInt(bigMoneyStr);
        } else {
            bigMoney = 0;
        }
        int currentPage = 0;//当前页面

        if (currentPagestr != null && currentPagestr.length() > 0) {
            currentPage = Integer.parseInt(currentPagestr);
        } else {
            //如果为空的话就设置默认值
            currentPage = 1;
        }
        int pageSize = 0;//当前页面
        if (pageSizestr != null && pageSizestr.length() > 0) {
            pageSize = Integer.parseInt(pageSizestr);
        } else {
            //如果为空的话就设置默认值
            pageSize = 8;
        }
        PageBean<Route> pb = routeService.findMyFavoriteRank(currentPage, pageSize, rname, smallMoney, bigMoney);
        writeValue(pb, response);

    }

    public void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        //获取是否有session值
        User user = (User) request.getSession().getAttribute("user");


//        System.out.println(user.getName());

        int uid;
        //判断用户是否登录
        if (user == null ) {
            //说明用户没有登录
            return;
        } else {
            uid = user.getUid();

        }
        ResultInfo info = new ResultInfo();

        System.out.println(user.getUsername()+"add");
        if (uid == 0) {


            User u = userService.login1(user.getUsername());


            uid = u.getUid();


        }
        System.out.println("cancel"+rid+":"+uid);
        favoriteService.cancelFavorite(rid,uid);


    }
}
