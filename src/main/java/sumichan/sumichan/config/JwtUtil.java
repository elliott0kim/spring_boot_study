package sumichan.sumichan.config;

import io.jsonwebtoken.*;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import sumichan.sumichan.dto.client.UserInfoDto;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Base64;

@Slf4j
@Component
public class JwtUtil {
    private final SecretKey key;
    private final long accessTokenExpTime;

    public JwtUtil(@Value("${jwt.private_secret_key}") String secretKey,
                   @Value("${jwt.expiration_time}") long accessTokenExpTime)
    {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        key = new SecretKeySpec(keyBytes, "HmacSHA256");

        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     * Access Token 생성
     * @param userInfoDto
     * @return Access Token String
     */
    public String createAccessToken(UserInfoDto userInfoDto) {
        return createToken(userInfoDto, accessTokenExpTime);
    }


    /**
     * JWT 생성
     * @param userInfoDto
     * @param expireTime
     * @return JWT String
     */
    private String createToken(UserInfoDto userInfoDto, long expireTime) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userInfoDto.getUserID())
                .setIssuer("elliott_kim")
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .setIssuedAt(Date.from(now.toInstant()))
                .compact();
    }


    /**
     * Token에서 User ID 추출
     * @param token
     * @return User ID
     */
    public String getUserId(String token) {
        return parseSub(token).get("userId", String.class);
    }


    /**
     * JWT 검증
     * @param token
     * @return IsValidate
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }


    /**
     * JWT Claims 추출
     * @param accessToken
     * @return JWT Claims
     */
    public Claims parseSub(String accessToken) {
        try {
            return Jwts.parser().
                    setSigningKey(key).
                    build().
                    parseClaimsJws(accessToken).
                    getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
