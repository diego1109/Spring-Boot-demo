package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Map;
import org.joda.time.DateTime;

public class JwtUtil {

  private static final String SIGN = "!Q@W#E$R%T^Y";

  /**
   * generate token
   * @param payLoad
   * @return
   */
  public static String getToken(Map<String, String> payLoad) {
    DateTime currentTime = DateTime.now();
    JWTCreator.Builder builder = JWT.create();
    payLoad.forEach(builder::withClaim);

    return builder.withExpiresAt(currentTime.plusDays(7).toDate()).sign(Algorithm.HMAC256(SIGN));
  }

  /**
   * verify token
   * @param token
   */
  public static void verify(String token) {
    JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
  }

  /**
   * get information from token
   * @param token
   * @return
   */
  public static DecodedJWT getJwtInformation(String token) {
    return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
  }

}
