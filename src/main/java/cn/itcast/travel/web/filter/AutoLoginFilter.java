package cn.itcast.travel.web.filter;


import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 获取登录用户信息

        User user = (User) request.getSession().getAttribute("user");
        //先判断Session中是否有用户信息,如果有,当前是登录状态,直接放行
        if (user != null) {
            //放行
            chain.doFilter(req, resp);
        } else {
            //如果没有,去cookie中查找用户信息
            String username = null;
            String password = null;
            Cookie[] cookies = request.getCookies();
            boolean flag = false;
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_auto")) {
                        String value = cookie.getValue();
                        String[] split = value.split("@");
                        username = split[0];
                        System.out.println(username);
                        password = split[1];
                        System.out.println(username + password);
                        flag = true;
                        break;
                    }
                }
            }

            if (flag == false) {
                //如果在cookie中找不到,直接放行
                chain.doFilter(request, resp);
            } else {
                //如果在cookie中找到了用户名和密码,拿着用户名和密码去数据库中校验
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(password);
                UserService service = new UserServiceImpl();
                boolean result = service.findByUserName(user1.getUsername());
                //校验失败,直接放行
                if (!result) {
                    chain.doFilter(request, resp);
                } else {
                    //校验通过,把用户信息保存到session中
                    request.getSession().setAttribute("user", user1);
                    chain.doFilter(req, resp);
                }
        }


    }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
