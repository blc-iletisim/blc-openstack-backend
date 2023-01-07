package com.blc.customerInterface.jwt;

import com.blc.customerInterface.graphql.blackToken.domain.BlackToken;
import com.blc.customerInterface.graphql.blackToken.repo.BlackTokenRepo;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.repo.UserRepo;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;


@Component
public class JwtTokenProvider implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserRepo userRepo;

    private final BlackTokenRepo blackTokenRepo;

    @Value("${security.jwt.token.secret-key}")
    private String APP_SECRET;
    @Value("${security.jwt.token.expires-in}")
    private long EXPIRES_IN;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    public JwtTokenProvider(UserRepo userRepo, BlackTokenRepo blackTokenRepo) {
        this.userRepo = userRepo;
        this.blackTokenRepo = blackTokenRepo;
    }

    public String generateJwtAccessToken(Authentication authentication){
        JwtUserDetailsImpl userPrincipal = (JwtUserDetailsImpl) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        User user=userRepo.findByEmail(userPrincipal.getEmail()).orElse(null);
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("id",user.getId().toString())
                .claim("user",user.getName())
                .claim("role",user.getRole().getName())
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRES_IN)).signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    public String getUserNameFromJwt(String token){
        /*
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
         */
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).setSigningKey(token);


            return !isTokenLogout(token) && !isTokenExpired(token);
        }catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    private boolean isTokenLogout(String token) {

        Optional<BlackToken> blackToken = blackTokenRepo.findByAccessToken(token);
        return blackToken.isPresent();
    }

    public UUID getUserIdFromJwt(String jwtToken) {
        Claims claim = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
        return UUID.fromString(claim.get("id",String.class));
    }

    public UserDetails getUserDetailsFromJwt(String jwtToken){
        String username = getUserNameFromJwt(jwtToken);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return userDetails;
    }


    public static String getAttribute(DataFetchingEnvironment environment, String key) {
        DefaultGraphQLServletContext context = environment.getContext();
        return context.getHttpServletRequest().getAttribute(key).toString();
    }

    public static String getHeader(DataFetchingEnvironment environment, String key) {
        DefaultGraphQLServletContext context = environment.getContext();
        return context.getHttpServletRequest().getHeader(key);
    }

    public static String getRemoteHost(DataFetchingEnvironment environment) {
        DefaultGraphQLServletContext context = environment.getContext();
        return context.getHttpServletRequest().getRemoteHost();
    }
}
