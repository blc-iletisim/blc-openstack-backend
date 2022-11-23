package com.blc.customerInterface.graphql.user.repo.critera.spec;

import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.critera.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserCriteriaSpec extends BaseCriteriaSpec<User, UserCriteria> {
    public static Specification<User> name(String name){
        return (name == null || name.trim().length() == 0) ? null : (Specification<User>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<User> email(String email){
        return (email == null || email.trim().length() == 0) ? null : (Specification<User>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }
    @Override
    public Specification<User> createForAll(UserCriteria criteria) {
        return Specification.where(name(criteria.getName()))
                .and(email(criteria.getEmail()));
    }
}
