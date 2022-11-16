package com.blc.customerInterface.graphql.instance.repo.criteria.spec;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.repo.criteria.InstanceCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class InstanceCriteriaSpec extends BaseCriteriaSpec<Instance, InstanceCriteria> {
    @Override
    public Specification<Instance> createForAll(InstanceCriteria criteria) {
        return null;
    }
}
