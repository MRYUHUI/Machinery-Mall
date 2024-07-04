package com.mall.mechmall.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 根据用户信息生成JWT Token
     *
     * @param id      用户ID
     * @param account 用户账号
     * @param role    用户角色
     * @return 生成的JWT Token
     */
    public String generateToken(Integer id, String account, Integer role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("account", account);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从JWT Token中获取用户信息
     *
     * @param token JWT Token
     * @return 包含用户信息的Claims对象
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证JWT Token是否有效
     *
     * @param token JWT Token
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT Token中获取用户ID
     *
     * @param token JWT Token
     * @return 用户ID
     */
    public Integer getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (Integer) claims.get("id");
    }

    /**
     * 从JWT Token中获取用户名
     *
     * @param token JWT Token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (String) claims.get("account");
    }

    /**
     * 从JWT Token中获取用户角色
     *
     * @param token JWT Token
     * @return 用户角色
     */
    public Integer getRoleFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (Integer) claims.get("role");
    }

    /**
     * 检查JWT Token是否过期
     *
     * @param token JWT Token
     * @return 是否过期
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getClaimsFromToken(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据用户名加载用户详细信息
     *
     * @param username 用户名
     * @return UserDetails 用户详细信息
     */
    public UserDetails getUserDetails(String username) {
        return userDetailsService.loadUserByUsername(username);
    }
}