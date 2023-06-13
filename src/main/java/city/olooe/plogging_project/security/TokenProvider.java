package city.olooe.plogging_project.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.MemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 박연재
 * 
 * @date: 2023.06.05
 * 
 * @brief: 토큰 공급 클래스
 */
@Service
@Slf4j
public class TokenProvider {

  @Value("${app.jwtSecretKey}")
  private String jwtSecretKey;

  @Value("${app.jwtExpiration}")
  private int jwtExpiration;

  public String userCreateToken(final MemberEntity memberEntity) {

    Date expiryDate = Date.from(Instant.now()
            .plus(jwtExpiration, ChronoUnit.DAYS));
    // String s = String.valueOf(member);
    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
        .setSubject(memberEntity.getUserId())
            .setIssuer("myApp")
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .compact();
  }

  public String validateAndGetUserId(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecretKey)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  public String oauthCreateToken(Authentication authentication) {
    ApplicationOAuth2User userPrincipal = (ApplicationOAuth2User) authentication.getPrincipal();
    Date expiryDate = Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.DAYS));

    return Jwts.builder()
      .setSubject(userPrincipal.getName())
      .setIssuedAt(new Date())
      .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
      .compact();
  }
}
