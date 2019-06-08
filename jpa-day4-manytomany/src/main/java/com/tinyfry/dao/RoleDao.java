package com.tinyfry.dao;

import com.tinyfry.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 21:56
 * @Version 1.0
 */
public interface RoleDao extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {
}
