package com.blc.customerInterface.jwt.logout;

import com.blc.customerInterface.graphql.refreshToken.service.RefreshTokenService;
import org.springframework.stereotype.Service;

@Service
public class LogoutTokenService {

    private final LogoutTokenRepository logoutTokenRepository;

    private final RefreshTokenService refreshTokenService;

    public LogoutTokenService(LogoutTokenRepository logoutTokenRepository, RefreshTokenService refreshTokenService) {
        this.logoutTokenRepository = logoutTokenRepository;
        this.refreshTokenService = refreshTokenService;
    }

    public void addLogoutToken(String accessToken) {
        logoutTokenRepository.save(new LogoutToken(accessToken));
    }

    public boolean isLogoutToken(String accessToken) {
        return logoutTokenRepository.findByAccessToken(accessToken).isPresent();
    }

    public boolean logoutRequest(LogoutRequest logoutRequest) {
        try {
            refreshTokenService.deleteByUserId(logoutRequest.getUserId());
            addLogoutToken(logoutRequest.getAccessToken());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
