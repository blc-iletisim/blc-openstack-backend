package com.blc.customerInterface.graphql.role.service;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.repo.RoleRepo;
import com.blc.customerInterface.graphql.role.repo.criteria.RoleCriteria;
import com.blc.customerInterface.graphql.role.repo.criteria.spec.RoleCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleRepo, RoleCriteria, RoleCriteriaSpec> {
    @Autowired
    public RoleService(RoleRepo repository, RoleCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
