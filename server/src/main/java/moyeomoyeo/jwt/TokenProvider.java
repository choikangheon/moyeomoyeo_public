package moyeomoyeo.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import moyeomoyeo.config.auth.PrincipalDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenProvider {

    private String secret = JwtProperties.SECRET;




    public String doGenerateToken(PrincipalDetails principalDetails){
        Date now = new Date();
        Claims claims = Jwts.claims();
        claims.put("memId",principalDetails.getMember().getMemId());
       // claims.put("memPw",principalDetails.getMember().getMemId());
        claims.put("memName",principalDetails.getMember().getMemName());

        return  Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS512,secret)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }
}
