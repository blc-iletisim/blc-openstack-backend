package com.blc.customerInterface.jwt.logout;

import com.blc.customerInterface.graphql.refreshToken.service.RefreshTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/logout")
public class LogoutTokenController {

    private final LogoutTokenService logoutTokenService;
    public LogoutTokenController(LogoutTokenService logoutTokenService, RefreshTokenService refreshTokenService) {
        this.logoutTokenService = logoutTokenService;
    }

    @GetMapping
    public boolean logoutRequest(LogoutRequest logoutRequest) {
        return logoutTokenService.logoutRequest(logoutRequest);
    }
}
