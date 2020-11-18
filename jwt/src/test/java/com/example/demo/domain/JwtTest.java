package com.example.demo.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

class JwtTest {

  DateTime currentTime = DateTime.now();
  private final String secret = "!Q@W#E$R%T^Y";

  @Test
  public void show_hello() {
    System.out.println("hello world");
  }

  @Test
  public void generate_jwt() {
    String token = JWT.create()
        .withClaim("userId",21)
        .withClaim("userName", "diego")
        .withExpiresAt(currentTime.plusHours(1).toDate())
        .sign(Algorithm.HMAC256(secret));

    System.out.println(token);
  }

  @Test
  public void valid_jwt(){
    // 创建验证对象
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
    // 验证token：判断前两部分加密后能否得到第三部分
    DecodedJWT result = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImRpZWdvIiwiZXhwIjoxNjA1Njg4MzQ1LCJ1c2VySWQiOjIxfQ.CP7GlUtA6qM3AXHjiLOm8k7Yu0LDaAi3KoBXZLPx0L8");

    System.out.println(result.getClaims().get("userId").asInt());
    System.out.println(result.getClaims().get("userName").asString());
    System.out.println(result.getExpiresAt());
  }
}