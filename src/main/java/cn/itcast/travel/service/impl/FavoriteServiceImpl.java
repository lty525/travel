package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 10:10$
 * @Version: 1.0
 */

public class FavoriteServiceImpl implements FavoriteService{
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 通过rid和uid查询用户是否有收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean findByUidAndRid(String rid, int uid) {
        Favorite favorite = favoriteDao.findByUidAndRid(Integer.parseInt(rid), uid);
        System.out.println(favorite);
        return favorite!=null;
    }

    /**
     * 根据rid 和uid来添加收藏
     * @param rid
     * @param uid
     */
    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

    /**
     * 取消收藏
     * @param rid
     * @param uid
     */
    @Override
    public void cancelFavorite(String rid, int uid) {
        favoriteDao.cancelFavorite(Integer.parseInt(rid),uid);
    }
}
