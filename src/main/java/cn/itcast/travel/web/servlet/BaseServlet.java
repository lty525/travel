package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseServlet被访问");
        //完成方法分发
        //1.获取请求路径
        String uri = req.getRequestURI();
//        System.out.println(uri);
        //2.截取路径的并获取方法名
        String methodname = uri.substring(uri.lastIndexOf("/") + 1);
//        System.out.println(methodname);
        //3.获取方法对象method
//        System.out.println(this);//cn.itcast.travel.web.servlet.UserServlet@4c661dcd
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化json
     * @param obj
     * @param response
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }
    public String writeValueasString(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       return mapper.writeValueAsString(obj);
    }
}
