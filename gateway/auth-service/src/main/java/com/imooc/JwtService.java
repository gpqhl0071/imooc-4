package com.imooc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JwtService {

  private static final long TOKEN_EXP_TIME = 60000;

  public String token(Account account) {

    Date now = new Date();

    Algorithm algorithm = Algorithm.HMAC256("changeIt");

    String token = JWT.create()
        .withIssuer("peng")
        .withIssuedAt(now)
        .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
        .withClaim("USER_NAME", account.getUsername())
        .sign(algorithm);

    log.info("jwt generated user={}", account.getUsername());
    return token;
  }

  public boolean verify(String token, String username) {
    log.info("verify jwt - username={}", username);
    try {
      Algorithm algorithm = Algorithm.HMAC256("changeIt");
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("peng")
          .withClaim("USER_NAME", username)
          .build();

      verifier.verify(token);

      return true;
    } catch (Exception e) {
      log.error("auth failed", e);
      return false;
    }
  }

}
