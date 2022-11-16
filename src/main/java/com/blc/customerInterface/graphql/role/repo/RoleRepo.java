package com.blc.customerInterface.graphql.role.repo;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends BaseRepo<Role> {
}
