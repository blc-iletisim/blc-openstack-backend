package com.blc.customerInterface.graphql.user.service;

import com.blc.customerInterface.lib.dao.service.BaseService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.graphql.user.repo.critera.UserCriteria;
import com.blc.customerInterface.graphql.user.repo.critera.spec.UserCriteriaSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepo, UserCriteria, UserCriteriaSpec> {

    @Autowired
    public UserService(UserRepo repository, UserCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
