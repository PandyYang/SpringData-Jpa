package com.tinyfry;

import com.tinyfry.dao.RoleDao;
import com.tinyfry.dao.UserDao;
import com.tinyfry.domain.Role;
import com.tinyfry.domain.User;
import org.hibernate.annotations.ColumnTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 22:11
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MantToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 多对多 被动的一方放弃维护权
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserName("fry");

        Role role = new Role();
        role.setRoleName("Pandy");

        user.getRoles().add(role);//配置用户到角色的关系中
        role.getUsers().add(user);//一方需要放弃维护权

        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 测试级联添加操作
     * 保存用户的同时保存用户的关联角色
     * cascade  = CasCade.ALL
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCasdeAdd(){
        User user = new User();
        Role role = new Role();

        user.setUserName("111");
        role.setRoleName("222");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
    }

    /**
     * 级联删除操作
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCasdeRemove(){
        //查询id为1的用户
        User one = userDao.findOne(1l);
        userDao.delete(one);
    }
}
