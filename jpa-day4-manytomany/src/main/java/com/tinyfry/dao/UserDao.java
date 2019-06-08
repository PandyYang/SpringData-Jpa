package com.tinyfry.dao;

import com.tinyfry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 21:55
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
}
