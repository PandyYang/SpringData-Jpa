package com.tinfry.test;

import com.tinyfry.dao.CustomerDao;
import com.tinyfry.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/6/7 17:21
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerTestDao {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        Customer one = customerDao.findOne(3l);
        System.out.println("one = " + one);
    }

    /**
     * 保存或者更新操作
     * 根据传递的对象是否存在主键 没有主键属性 保存
     * 存在id主键属性 根据id查询数据 更新数据
     */
    /**
     * 以下没有主键 是保存操作
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustAddress("增加1");
        customer.setCustIndustry("123");
        customer.setCustSource("123");
        customer.setCustPhone("1233333");
        customer.setCustName("333");
        customerDao.save(customer);
        //Assert.assertEquals();
    }

    /**
     * 以下带有主键是更新操作
     */
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustAddress("更新1");
        customer.setCustIndustry("123");
        customer.setCustSource("123");
        customer.setCustPhone("1233333");
        customer.setCustName("333");
        customer.setCustId(4l);
        customerDao.save(customer);
    }
    /**
     * 删除操作
     */
    @Test
    public void testDelete(){
        customerDao.delete(4l);
    }

    /**
     * 查询所有的客户
     */
    @Test
    public void testFindAll(){
        List<Customer> all = customerDao.findAll();
        System.out.println("all = " + all);
    }
}
