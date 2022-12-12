package com.blc.customerInterface.graphql.company.repo.criteria.spec;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.repo.criteria.CompanyCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CompanyCriteriaSpec extends BaseCriteriaSpec<Company, CompanyCriteria> {
    @Override
    public Specification<Company> createForAll(CompanyCriteria criteria) {
        return null;
    }
}

