package com.tinyfry.test;

import com.tinyfry.domain.Customer;
import com.tinyfry.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Author: Pandy
 * @Date: 2019/6/5 10:50
 * @Version 1.0
 */
public class JpaTest {
    @Test
    public void testSave(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("tinyfry");
        customer.setCustIndustry("teacher");
        customer.setCustAddress("111");
        customer.setCustLevel("123");
        customer.setCustPhone("1234");
        customer.setCustSource("123");
        manager.persist(customer);
        tx.commit();
        manager.close();
        factory.close();
    }

    /**
     * 通过抽取的工具类进行保存客户
     */
    @Test
    public void test2(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("fry");
        customer.setCustIndustry("doctor");
        customer.setCustAddress("1");
        customer.setCustLevel("1");
        customer.setCustPhone("1");
        customer.setCustSource("1");
        manager.persist(customer);
        tx.commit();
        manager.close();
        //factory.close();
    }

    /**
     * 根据id查询客户
     */
    @Test
    public void testFind(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.find(Customer.class,1l);
        System.out.println("customer = " + customer.toString());
        transaction.commit();
        manager.close();
    }

    /**
     * find方法与getReference方法
     * 调用getReference不会发送sql语句 只有在使用查询结果对象的时候才会发送相应的查询语句
     */
    @Test
    public void testFind2(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.getReference(Customer.class,1l);
        System.out.println("customer = " + customer.toString());
        transaction.commit();
        manager.close();
    }

    /**
     * 删除用户
     */
    @Test
    public void testRemove(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //根据id查询客户 然后调用remove方法完成删除操作
        Customer customer = manager.find(Customer.class, 1l);
        manager.remove(customer);
        transaction.commit();
        manager.close();
    }


    @Test
    public void testUpdate(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //先查询客户 然后更新客户
        Customer customer = manager.find(Customer.class, 2l);
        customer.setCustIndustry("修改");
        manager.merge(customer);
        transaction.commit();
        manager.close();
    }



}
