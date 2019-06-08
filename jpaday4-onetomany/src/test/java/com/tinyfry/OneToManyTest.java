package com.tinyfry;

import com.tinyfry.dao.CustomerDao;
import com.tinyfry.dao.LinkmanDao;
import com.tinyfry.domain.Customer;
import com.tinyfry.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 15:53
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkmanDao linkmanDao;
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        //创建一个客户 一个联系人
        Customer customer = new Customer();
        customer.setCustName("zijie");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("douyin");
        //配置了客户和联系人之间的关系
            //从客户的角度 发送两条insert语句 然后是一条更新语句
        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkmanDao.save(linkMan);

    }

    /**
     * 级联添加
     * 注意一方维护外键即可 即一的一方进行维护
     * 现在将Customer中的外键上的注解进行相关的修改
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("baidu");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("Xuez");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }

    /**
     * 级联删除 删除一号客户的同时 删除一号客户的所有联系人
     *
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove(){
        Customer customer = customerDao.findOne(1l);
        customerDao.delete(customer);
    }
}
