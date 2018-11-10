package cn.itcast.travel.service;

import cn.itcast.travel.domain.User; /**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/9$ 13:26$
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 注册方法
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 激活方法
     * @param code
     * @return
     */

    boolean active(String code);

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);


    /**
     * 通过username获取uid
     * @param username
     * @return
     */
    User login1(String username);

    boolean findByUserName(String username);
}
