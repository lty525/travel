package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.RAndRimg;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IndexService;
import cn.itcast.travel.service.impl.IndexServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index/*")

public class IndexServlet extends BaseServlet {
    private IndexService indexService = new IndexServiceImpl();

    /**
     * 最热门旅游
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void hotTravel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = indexService.route1(4);
        writeValue(routes,response);

        //获取一个List<Route>集合



    }

    /**
     * 最新路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void newRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = indexService.route2(4);
        writeValue(routes,response);
    }

    /**
     * 主题路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void topicRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = indexService.route3(4);
        writeValue(routes,response);
    }
    public void guoneiRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = indexService.route4(8);
        writeValue(routes,response);
    }
    public void jingwaiRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = indexService.route5(8);
        writeValue(routes,response);
    }

    /**
     * 每页的推荐
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void hotRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RAndRimg> routes = indexService.route6(5);
        writeValue(routes, response);
    }
    }
