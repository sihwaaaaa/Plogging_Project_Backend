package city.olooe.plogging_project.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.MemberEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenProvider {
  private static final String SECRET_KEY = "12345";

  public String create(MemberEntity memberEntity){
    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
    // String s = String.valueOf(member);
    return Jwts.builder()
      .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
      // .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
      .setSubject(memberEntity.getUserId())
      .setIssuer("myApp")
      .setIssuedAt(new Date())
      .setExpiration(expiryDate)
      .compact();    
  }

  public String validateAndGetUserId(String token){
    Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();  
  }
}
