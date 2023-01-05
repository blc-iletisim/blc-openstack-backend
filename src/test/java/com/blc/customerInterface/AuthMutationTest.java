package com.blc.customerInterface;

import com.blc.customerInterface.exception.CustomException;
import com.blc.customerInterface.graphql.auth.domain.JwtResponse;
import com.blc.customerInterface.graphql.refreshToken.domain.RefreshToken;
import com.blc.customerInterface.graphql.refreshToken.service.RefreshTokenService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import com.blc.customerInterface.jwt.JwtTokenProvider;
import com.blc.customerInterface.jwt.JwtUserDetailsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.UUID;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthMutationTest {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  JwtTokenProvider jwtTokenProvider;
    @Autowired
    private  RefreshTokenService refreshTokenService;

    @Test
    @Order(1)
    public void loginTest(){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("admin@blc.com","admin");
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateJwtAccessToken(authentication);
        User user = userRepo.findByEmail("admin@blc.com")
                .orElseThrow(
                        () -> new CustomException(
                                String.format("User %s does not exist", "admin@blc.com"),
                                HttpStatus.BAD_REQUEST)
                );
        RefreshToken resfreshToken = refreshTokenService.createRefreshToken(user.getId());

        JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) authentication.getPrincipal();
        System.out.println("Tokennnnnnnnnnnnnnnnnnn "+token.toString());

        JwtResponse jwtResponse= JwtResponse.builder()
                .accessToken(token)
                .refreshToken(resfreshToken.getToken())
                .uid(userDetails.getId())
                .role(user.getRole().getName())
                .email(userDetails.getEmail())
                .user(user)
                .build();

        Assertions.assertThat(jwtResponse.getAccessToken());

    }

}
