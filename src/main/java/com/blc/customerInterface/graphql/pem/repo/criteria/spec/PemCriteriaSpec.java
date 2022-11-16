package com.blc.customerInterface.graphql.pem.repo.criteria.spec;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.repo.criteria.PemCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PemCriteriaSpec extends BaseCriteriaSpec<Pem, PemCriteria> {
    @Override
    public Specification<Pem> createForAll(PemCriteria criteria) {
        return null;
    }
}
