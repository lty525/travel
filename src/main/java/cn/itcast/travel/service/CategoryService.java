package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 13:01$
 * @Version: 1.0
 */
public interface CategoryService {
    public List<Category> findAll();
}
