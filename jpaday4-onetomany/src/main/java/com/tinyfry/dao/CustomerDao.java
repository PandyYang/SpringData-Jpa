package com.tinyfry.dao;

import com.tinyfry.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 14:26
 * @Version 1.0
 */
public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {
}
