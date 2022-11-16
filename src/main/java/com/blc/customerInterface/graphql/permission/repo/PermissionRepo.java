package com.blc.customerInterface.graphql.permission.repo;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends BaseRepo<Permission> {
}
