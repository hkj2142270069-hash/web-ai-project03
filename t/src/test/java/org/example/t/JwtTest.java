package org.example.t;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    @Test
    public void testGenenerateJwt() {
        Map<String,Object> datMap=new HashMap<>();
        datMap.put("id",1);
        datMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aGtq")
                .addClaims(datMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NDI0NjA5NH0.YqZdW0IA5iBa0h-14o9tGivtslAWsTGlXbgHV1GGGcw";
        Claims claims = Jwts.parser().setSigningKey("aGtq")
                .parseClaimsJws(jwt)


                .getBody();
        System.out.println(claims);
    }
}
