package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/10$ 13:02$
 * @Version: 1.0
 */

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();

   /*    @Override
    public List<Category> findAll() {
        //从redis查询缓存
        Jedis jedis = JedisUtil.getJedis();
        List<String> category = jedis.lrange("category", 0, -1);
        //判断是否有
        List<Category> list=null;
        if (category==null||category.size()==0){
            System.out.println("没有缓存");
            //没有缓存
            //从数据库进行查询
             list = dao.findAll();
            for (Category category1 : list) {
                jedis.lpush("category",category1.getCname());
            }

        }
        else{
            System.out.println("有缓存");
            //有缓存 将查询到的添加到集合中
            list = new ArrayList<Category>();
            for (String s : category) {
                Category category1 = new Category();
                category1.setCname(s);
                list.add(category1);
            }

        }

        return list;
    }*/
    @Override
    public List<Category> findAll() {
        //从redis查询缓存
        Jedis jedis = JedisUtil.getJedis();
//      Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        //判断是否有缓存
        List<Category> cs = null;
        if (categorys == null || categorys.size() == 0) {
            //没有缓存从数据库进行查询
            cs = dao.findAll();
            //把集合存储到redis里面
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            //有缓存从缓存中查询
            cs = new ArrayList<Category>();
            for (Tuple category : categorys) {
                //设置路线名和cid
                Category category1 = new Category();
                category1.setCname(category.getElement());
                category1.setCid((int) category.getScore());
                cs.add(category1);

            }
        }
        return cs;

    }
}
