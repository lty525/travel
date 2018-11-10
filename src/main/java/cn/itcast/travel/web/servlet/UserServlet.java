package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //抽取Service
    private UserService userService = new UserServiceImpl();
    private UserDao dao = new UserDaoImpl();

    /**
     * 不是自动登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// /判断验证码是否正确
        //先获取验证码
        String check = request.getParameter("check");
//        System.out.println(check);
        HttpSession session = request.getSession();
        //获取sessio中的验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止复用
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //如果验证码错误
            //注册失败
            ResultInfo info = new ResultInfo();
            //失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误.");
            //序列化注册信息
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //将json回写到客户端
            //设置bianm
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用Service方法
//            UserService userService = new UserServiceImpl();
        User u = userService.login(user);
        //判断是否为登录成功 使用异步请求的方式
        ResultInfo info = new ResultInfo();
        if (u == null) {
            //用户名和密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断用户是否激活
        if (u != null && "Y".equals(u.getStatus())) {
            //用户激活并且用户名和密码正确
            request.getSession().setAttribute("user", u);
            Cookie cookie = new Cookie("user_auto", u.getUsername() + "@" + u.getPassword());
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            info.setFlag(true);
        }
        if (u != null && !"Y".equals(u.getStatus())) {

            //表示尚未激活
            info.setFlag(false);
            info.setErrorMsg("尚未激活,请查看邮箱进行激活");
        }
        //将info序列化 响应数据
            /*ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getOutputStream(), info);*/
        writeValue(info, response);

    }

    /**
     * 自动登录方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loginZD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// /判断验证码是否正确
        //先获取验证码
        String check = request.getParameter("check");
        System.out.println(check);
        HttpSession session = request.getSession();
        //获取sessio中的验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止复用
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //如果验证码错误
            //注册失败
            ResultInfo info = new ResultInfo();
            //失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误.");
            //序列化注册信息
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //将json回写到客户端
            //设置bianm
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用Service方法
//            UserService userService = new UserServiceImpl();
        User u = userService.login(user);
        //判断是否为登录成功 使用异步请求的方式
        ResultInfo info = new ResultInfo();
        if (u == null) {
            //用户名和密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //判断用户是否激活
        if (u != null && "Y".equals(u.getStatus())) {
            //用户激活并且用户名和密码正确
            request.getSession().setAttribute("user", u);
            Cookie cookie = new Cookie("user_auto", u.getUsername() + "@" + u.getPassword());
            cookie.setMaxAge(60 * 60 * 24);
//            cookie.setPath("/");
            response.addCookie(cookie);


            info.setFlag(true);
        }
        if (u != null && !"Y".equals(u.getStatus())) {

            //表示尚未激活
            info.setFlag(false);
            info.setErrorMsg("尚未激活,请查看邮箱进行激活");
        }
        //将info序列化 响应数据
            /*ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getOutputStream(), info);*/
        writeValue(info, response);

    }


    /**
     * 注册方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // /判断验证码是否正确
        //先获取验证码
        String check = request.getParameter("check");
//        System.out.println(check);
        HttpSession session = request.getSession();
        //获取sessio中的验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        System.out.println(checkcode_server);
//        System.out.println("卫栖梧群二群无");
        //防止复用
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //如果验证码错误
            //注册失败
            ResultInfo info = new ResultInfo();
            //失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误.");
            //序列化注册信息
//            ObjectMapper mapper = new ObjectMapper();
            String json = writeValueasString(info);
            //将json回写到客户端
            //设置bianm
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //进行
        //获取请求消息
        Map<String, String[]> map = request.getParameterMap();
        //创建User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//            UserService userService = new UserServiceImpl();
//            System.out.println(user.getName());
        boolean flag = userService.regist(user);
        ResultInfo info = new ResultInfo();
        //判断注册是否成功
        if (flag) {
            //成功
            info.setFlag(true);
        } else {
            //失败
            info.setFlag(false);
            info.setErrorMsg("注册失败...");
        }
        //序列化注册信息
//            ObjectMapper mapper = new ObjectMapper();
        String json = writeValueasString(info);
        //将json回写到客户端
        //设置bianm
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }


    /**
     * 退出方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        //清除Cookie缓存
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("user_auto".equals(cookie.getName())) {
//                    System.out.println(1);
//                    cookie.setPath("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        //重定向到首页
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    /**
     * 获取单个user方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String username = null;
//        String uid = null;
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
//                    System.out.println("cookie2");
                if (cookie.getName().equals("user_auto")) {
                    String value = cookie.getValue();
                    String[] split = value.split("@");
                    username = split[0];
//                    uid = split[1];
//                    System.out.println(uid + "getOne");
//                    user.setUid(Integer.parseInt(uid));
                    user.setUsername(username);

                }
            }
        }

        if (flag = false) {
            user = (User) request.getSession().getAttribute("user");

        }
        writeValue(user, response);


    }

    /**
     * 激活方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //调用service进行激活
//            UserService userService = new UserServiceImpl();
            //调用激活方法
            boolean flag = userService.active(code);
            String msg = null;
            if (flag) {
                //激活成功 调转到登录页面
                msg = "激活成功,点击<a href='http://localhost/travel/login.html'>登录<a>";
            } else {
                //激活失败
                msg = "激活失败,请联系管理员!!";
            }
            //回写
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }


}
