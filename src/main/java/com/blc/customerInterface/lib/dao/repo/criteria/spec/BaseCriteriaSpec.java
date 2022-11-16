package com.blc.customerInterface.lib.dao.repo.criteria.spec;

import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.blc.customerInterface.lib.dao.repo.criteria.BaseCriteria;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseCriteriaSpec <E extends BaseDomain, C extends BaseCriteria>{
    public abstract Specification<E> createForAll(C criteria);

}
