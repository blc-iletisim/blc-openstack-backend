package com.blc.customerInterface.graphql.instance.repo;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstanceRepo extends BaseRepo<Instance> {
    Optional<Instance> findByName(String deneme);
}
