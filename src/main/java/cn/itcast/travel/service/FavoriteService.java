package cn.itcast.travel.service;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 10:09$
 * @Version: 1.0
 */
//收藏路线
public interface FavoriteService {
    /**
     * 通过rid和uid查询用户是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean findByUidAndRid(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);

    /**
     * 取消收藏
     * @param rid
     * @param uid
     */
    void cancelFavorite(String rid, int uid);
}
