package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/9$ 13:27$
 * @Version: 1.0
 */

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user=null;
        try {
            //定义sql语句
            String sql = "select * from tab_user where username=?";
            //查询语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }
        catch (Exception e){

        }

        return user;
    }

    @Override
    public void save(User user) {
        //把注册的用户存储在数据库中
        //定义sql语句
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //插入语句
        template.update(sql, user.getUsername(),
                user.getPassword()
                , user.getName()
                , user.getBirthday()
                , user.getSex()
                , user.getTelephone()
                , user.getEmail()
                ,user.getStatus()
                ,user.getCode()
        );

    }

    /**
     * 根据激活码来查询用户
     * @param code
     * @return
     */
    @Override
    public User findBycode(String code) {
        User user = null;
        try{
        //定义sql语句
        String sql = "select * from tab_user where code=?";
         user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code);
        }catch (Exception e){
        }
        return user;
    }

    /**
     * 根据用户来修改用户的激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //定义sql语句
        String sql = "update tab_user set status='Y'where uid = ? ";
        template.update(sql,user.getUid());

    }

    /**
     * 检验登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserAndPassword(String username, String password) {
        User user=null;
        try {
            //定义sql语句
            String sql = "select * from tab_user where username=? AND password=?";
            //查询语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        }
        catch (Exception e){

        }

        return user;
    }

    @Override
    public User login1(String username) {
        User user=null;
        try {
            //定义sql语句
            String sql = "select * from tab_user where username=? ";
            //查询语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }
        catch (Exception e){

        }

        return user;
    }

    @Override
    public User findUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

}
