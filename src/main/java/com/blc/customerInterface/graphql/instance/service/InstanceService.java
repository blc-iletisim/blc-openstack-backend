package com.blc.customerInterface.graphql.instance.service;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.repo.InstanceRepo;
import com.blc.customerInterface.graphql.instance.repo.criteria.InstanceCriteria;
import com.blc.customerInterface.graphql.instance.repo.criteria.spec.InstanceCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceService extends BaseService<Instance, InstanceRepo, InstanceCriteria, InstanceCriteriaSpec> {
    @Autowired
    public InstanceService(InstanceRepo repository, InstanceCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
