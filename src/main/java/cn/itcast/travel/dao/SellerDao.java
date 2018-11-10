package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/11$ 16:47$
 * @Version: 1.0
 */
public interface SellerDao {
    /**
     * 根据cid来查询商家的详细信息
     * @param sid
     * @return
     */
    Seller findSeller(int sid);
}
