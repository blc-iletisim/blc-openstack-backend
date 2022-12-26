package com.blc.customerInterface.graphql.company.repo;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends BaseRepo<Company> {
    Company findByName(String name);
}
