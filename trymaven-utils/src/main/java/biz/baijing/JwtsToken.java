package biz.baijing;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtsToken {

    private static String key = "baijing09872347jingbizbaij" +
            "bai5430w38950825348t40948t43to9erfrejoif3409tu09jhlkjlba" +
            "ijingbizbaijingbizingbizbaijzbaijingbiz";
    private static Long ttlMillis = (long)(1000 * 60 * 60 * 24);

    public static String createJwt(Map<String, Object> claims) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;



        String jwts = Jwts.builder()
                        .claims(claims)
                        .signWith(signatureAlgorithm, key)
                        .expiration(new Date(System.currentTimeMillis() + ttlMillis))
                         .compact();

        return jwts;
    }

    public static Claims parseJwt(String jwt) {
        Claims claimsJws = Jwts.parser()
                                .setSigningKey(key)
                                .build()
                                .parseSignedClaims(jwt)
                                .getBody();

        return claimsJws;
    }

}
