package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 10:11$
 * @Version: 1.0
 */

public interface FavoriteDao {
    /**
     * 通过rid和uid查询用户是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByUidAndRid(int rid,int uid);

    /**
     * 添加收藏
     * @param i
     * @param uid
     */
    void add(int i, int uid);

    /**
     * 取消收藏
     * @param rid
     * @param uid
     */

    void cancelFavorite(int rid, int uid);
}
