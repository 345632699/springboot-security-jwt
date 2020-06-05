package com.hotel.minapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;

public class JwtHelper {
  private Long EXPIRATION_TIME;
  private String SECRET;
  private final String TOKEN_PREFIX = "Bearer";
  private final String HEADER_STRING = "Authorization";
 
  public JwtHelper(String secret, long expire) {
    this.EXPIRATION_TIME = expire;
    this.SECRET = secret;
    System.out.println("正在初始化Jwthelper，expire="+expire);
  }
 
  public JSONObject generateToken(Map<String, Object> claims) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.SECOND, EXPIRATION_TIME.intValue());
    Date d = c.getTime();
    String jwt = Jwts.builder()
        .setClaims(claims)
        .setExpiration(d)
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    JSONObject json = new JSONObject();
    json.put("token",TOKEN_PREFIX + " " + jwt);
    json.put("token-type", TOKEN_PREFIX);
    json.put("expire-time",new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(d) );
    return json;
  }
 
  public Map<String, Object> validateTokenAndGetClaims(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    System.out.println("token is:"+token);
    if (token == null) {
      return null;
    } 
    Map<String, Object> body = Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody();
    return body;
  }
}