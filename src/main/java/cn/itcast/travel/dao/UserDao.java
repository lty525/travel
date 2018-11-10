package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User; /**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/9$ 13:27$
 * @Version: 1.0
 */

public interface UserDao {
    /**
     * 通过用户名查询用户是否存在
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 添加用户
     * @param user
     */
    public void save(User user);

    User findBycode(String code);

    void updateStatus(User user);

    User findUserAndPassword(String username, String password);

    User login1(String username);

    User findUsername(String username);
}
