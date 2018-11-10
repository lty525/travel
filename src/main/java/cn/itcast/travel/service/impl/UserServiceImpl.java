package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/9$ 13:27$
 * @Version: 1.0
 */

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    /**
     * 注册方法
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        System.out.println(user);
        //先查询用户名是否存在
        User user1 = dao.findByUsername(user.getUsername());
        if (user1!=null){
            //如果查询到有用户的存在
            return false;
        }
        //保存用户信息 注册成功
        //设置激活码,唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活状态 为为激活
        user.setStatus("N");
        dao.save(user);
        //激活邮件发送
        String content = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>请点击激活[旅游网]</a>";
//        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击一下[大宝宝的惊喜]</a>";
        //发送邮件
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活方法
     * @param code
     * @return
     */

    @Override
    public boolean active(String code) {
        //根据激活码查询用户
       User user =  dao.findBycode(code);
       if(user!=null){
           //有激活码
           //调用修改激活码状态
           dao.updateStatus(user);
           return true;
       }
       else {

        return false;
       }
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        return dao.findUserAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User login1(String username) {
        return dao.login1(username);
    }

    @Override
    public boolean findByUserName(String username) {
        User username1 = dao.findUsername(username);
        if (username1==null){
            return false;
        }
        else {
            return true;
        }
    }
}
