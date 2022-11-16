package com.blc.customerInterface.graphql.flavor.repo.criteria.spec;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.repo.criteria.FlavorCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FlavorCriteriaSpec extends BaseCriteriaSpec<Flavor, FlavorCriteria> {
    @Override
    public Specification<Flavor> createForAll(FlavorCriteria criteria) {
        return null;
    }
}
