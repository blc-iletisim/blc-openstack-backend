package com.blc.customerInterface.graphql.pem.service;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.repo.PemRepo;
import com.blc.customerInterface.graphql.pem.repo.criteria.PemCriteria;
import com.blc.customerInterface.graphql.pem.repo.criteria.spec.PemCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PemService extends BaseService<Pem, PemRepo, PemCriteria, PemCriteriaSpec> {
    @Autowired
    public PemService(PemRepo repository, PemCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
