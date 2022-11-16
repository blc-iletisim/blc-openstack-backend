package com.blc.customerInterface.graphql.permission.service;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.repo.PermissionRepo;
import com.blc.customerInterface.graphql.permission.repo.criteria.PermissionCriteria;
import com.blc.customerInterface.graphql.permission.repo.criteria.spec.PermissionCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseService<Permission, PermissionRepo, PermissionCriteria, PermissionCriteriaSpec> {
    @Autowired
    public PermissionService(PermissionRepo repository, PermissionCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
