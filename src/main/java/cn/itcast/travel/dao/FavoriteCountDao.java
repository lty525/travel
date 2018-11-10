package cn.itcast.travel.dao;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 11:26$
 * @Version: 1.0
 */
//查询收藏次数的dao
public interface FavoriteCountDao {
    int findFavoriteCountByrid(int rid);
}
