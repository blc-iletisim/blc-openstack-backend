package com.blc.customerInterface.graphql.company.service;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.repo.CompanyRepo;
import com.blc.customerInterface.graphql.company.repo.criteria.CompanyCriteria;
import com.blc.customerInterface.graphql.company.repo.criteria.spec.CompanyCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends BaseService<Company, CompanyRepo, CompanyCriteria, CompanyCriteriaSpec> {
    @Autowired
    public CompanyService(CompanyRepo repository, CompanyCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}


