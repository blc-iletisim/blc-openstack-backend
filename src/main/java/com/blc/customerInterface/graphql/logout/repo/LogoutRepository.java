package com.blc.customerInterface.graphql.logout.repo;

import com.blc.customerInterface.graphql.logout.domain.BlackToken;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogoutRepository extends JpaRepository<BlackToken, Long> {


    //save token
    @NotNull
    BlackToken save(@NotNull String token);


}
