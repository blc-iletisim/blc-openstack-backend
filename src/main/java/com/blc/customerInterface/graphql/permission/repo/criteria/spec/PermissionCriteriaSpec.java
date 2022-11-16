package com.blc.customerInterface.graphql.permission.repo.criteria.spec;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.repo.criteria.PermissionCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PermissionCriteriaSpec extends BaseCriteriaSpec<Permission, PermissionCriteria> {
    @Override
    public Specification<Permission> createForAll(PermissionCriteria criteria) {
        return null;
    }
}
