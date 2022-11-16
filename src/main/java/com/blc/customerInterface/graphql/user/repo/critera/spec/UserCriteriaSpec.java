package com.blc.customerInterface.graphql.user.repo.critera.spec;

import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.critera.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserCriteriaSpec extends BaseCriteriaSpec<User, UserCriteria> {
    @Override
    public Specification<User> createForAll(UserCriteria criteria) {
        return null;
    }
}
