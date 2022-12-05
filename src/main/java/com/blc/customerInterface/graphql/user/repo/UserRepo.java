package com.blc.customerInterface.graphql.user.repo;

import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.pem.Pem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepo<User> {
    Optional<User> findByEmail(String username);

}
