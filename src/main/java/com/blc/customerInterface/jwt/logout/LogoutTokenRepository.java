package com.blc.customerInterface.jwt.logout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoutTokenRepository extends JpaRepository<LogoutToken, Long> {
    Optional<LogoutToken> findByAccessToken(String accessToken);

}
