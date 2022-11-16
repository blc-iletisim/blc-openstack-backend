package com.blc.customerInterface.graphql.pem.repo;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PemRepo extends BaseRepo<Pem> {
}
