package com.blc.customerInterface.lib.dao.repo;


import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;


public interface BaseRepo<E extends BaseDomain> extends JpaRepository<E, UUID>, JpaSpecificationExecutor<E> {

}
