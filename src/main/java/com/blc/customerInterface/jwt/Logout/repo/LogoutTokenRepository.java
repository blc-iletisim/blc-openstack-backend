package com.blc.customerInterface.jwt.Logout.repo;

import com.blc.customerInterface.jwt.Logout.domain.LogoutToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogoutTokenRepository extends JpaRepository<LogoutToken, String> {

    Optional<LogoutToken> findByLogoutToken(String logoutToken);
}
