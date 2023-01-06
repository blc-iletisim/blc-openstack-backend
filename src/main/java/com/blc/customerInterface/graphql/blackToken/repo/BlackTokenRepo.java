package com.blc.customerInterface.graphql.blackToken.repo;

import com.blc.customerInterface.graphql.blackToken.domain.BlackToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackTokenRepo extends JpaRepository<BlackToken, Long> {

    Optional<BlackToken> findByAccessToken(String accessToken);


}
