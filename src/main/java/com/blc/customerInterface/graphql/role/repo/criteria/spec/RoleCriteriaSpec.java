package com.blc.customerInterface.graphql.role.repo.criteria.spec;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.repo.criteria.RoleCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleCriteriaSpec extends BaseCriteriaSpec<Role, RoleCriteria> {
    public static Specification<Role> name(String name){
        return (name == null || name.trim().length() == 0) ? null : (Specification<Role>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    @Override
    public Specification<Role> createForAll(RoleCriteria criteria) {
        return Specification.where(name(criteria.getName()));
    }
}
