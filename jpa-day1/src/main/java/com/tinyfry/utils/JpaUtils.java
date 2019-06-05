package com.tinyfry.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Author: Pandy
 * @Date: 2019/6/5 11:44
 * @Version 1.0
 * 程序第一次访问的时候加载公共的对象 避免频繁创建带来的性能开销
 * 解决实体管理器工厂浪费资源和耗时问题
 * 通过静态代码块的方式 当程序第一次访问此工具类的时候 创建一个公共的实体管理器工厂
 */
public class JpaUtils {
    private static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    /**
     * 获取EntityManager对象
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
