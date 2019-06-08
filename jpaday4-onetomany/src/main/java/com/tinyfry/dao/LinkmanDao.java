package com.tinyfry.dao;

import com.tinyfry.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Pandy
 * @Date: 2019/6/8 14:27
 * @Version 1.0
 */
public interface LinkmanDao extends JpaRepository<LinkMan,Long>,JpaSpecificationExecutor<LinkMan> {
}
