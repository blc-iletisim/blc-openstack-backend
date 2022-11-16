package com.blc.customerInterface.graphql.flavor.service;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.repo.FlavorRepo;
import com.blc.customerInterface.graphql.flavor.repo.criteria.FlavorCriteria;
import com.blc.customerInterface.graphql.flavor.repo.criteria.spec.FlavorCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlavorService extends BaseService<Flavor, FlavorRepo, FlavorCriteria, FlavorCriteriaSpec> {
    @Autowired
    public FlavorService(FlavorRepo repository, FlavorCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
