package com.blc.customerInterface.graphql.flavor.repo;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorRepo extends BaseRepo<Flavor> {
}
