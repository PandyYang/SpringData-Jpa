package com.tinyfry.test;

import com.tinyfry.domain.Customer;
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
}
