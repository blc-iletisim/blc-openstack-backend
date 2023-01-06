package com.blc.customerInterface.graphql.auth.mutation;

import com.blc.customerInterface.exception.CustomException;
import com.blc.customerInterface.exception.TokenRefreshException;
import com.blc.customerInterface.graphql.auth.domain.JwtResponse;
import com.blc.customerInterface.graphql.auth.mutation.input.AuthLoginInput;
import com.blc.customerInterface.graphql.auth.mutation.input.AuthLogoutInput;
import com.blc.customerInterface.graphql.blackToken.domain.BlackToken;
import com.blc.customerInterface.graphql.blackToken.repo.BlackTokenRepo;
import com.blc.customerInterface.graphql.refreshToken.domain.RefreshToken;
import com.blc.customerInterface.graphql.refreshToken.service.RefreshTokenService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.jwt.JwtTokenProvider;
import com.blc.customerInterface.jwt.JwtUserDetailsImpl;
import com.blc.customerInterface.jwt.RefreshToken.TokenRefreshRequest;
import com.blc.customerInterface.jwt.RefreshToken.TokenRefreshResponse;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Component
@Validated
public class AuthMutation implements GraphQLMutationResolver {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;

    private final BlackTokenRepo blackTokenRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;


    @Autowired
    public AuthMutation(AuthenticationManager authenticationManager, UserRepo userRepo, BlackTokenRepo blackTokenRepo, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.blackTokenRepo = blackTokenRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }



    public JwtResponse login(AuthLoginInput input) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(input.getEmail(),input.getPassword());
    Authentication authentication = authenticationManager.authenticate(authToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateJwtAccessToken(authentication);
    User user = userRepo.findByEmail(input.getEmail())
                .orElseThrow(
                        () -> new CustomException(
                                String.format("User %s does not exist", input.getEmail()),
                                HttpStatus.BAD_REQUEST)
                );
    RefreshToken resfreshToken = refreshTokenService.createRefreshToken(user.getId());

      JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) authentication.getPrincipal();

        JwtResponse jwtResponse=new JwtResponse(
        token,
        resfreshToken.getToken(),
        userDetails.getId(),
        user.getRole().getName(),
        userDetails.getEmail(),
        user);
        return jwtResponse;


    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtTokenProvider.generateTokenFromUsername(user.getEmail());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    public boolean logout(AuthLogoutInput input) {
        try {
            BlackToken blackToken = new BlackToken();
            blackToken.setAccessToken(input.getAccessToken());
            refreshTokenService.deleteByUserId(input.getUserId());
            blackTokenRepo.save(blackToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
