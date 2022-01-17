package moyeomoyeo.jwt;


import io.jsonwebtoken.*;
import moyeomoyeo.config.auth.PrincipalDetails;
import moyeomoyeo.entity.Member;
import moyeomoyeo.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override // 인증이나 권한이 필요한 주소요청이 왔을때
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);
        System.out.println("인증이나 권한이 필요필터->");

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("doFilterInternal jwtHeader : "+jwtHeader);

        //jwt 토큰을 검증을 해서 정상적인 사용자인지 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            System.out.println("비정상적인 사용자");
            return;
        }

        //JWT 토큰을 검증 해서 정상적인 사용자인지 확인
        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        try {
            Claims claims = Jwts.parser().setSigningKey
                            (JwtProperties.SECRET)
                    .parseClaimsJws(token).getBody();
            if (claims != null) {
                System.out.println("토큰정상");

                Member memberEntity = memberRepository.findByMemId((claims.get("memId")).toString());
                System.out.println("JwtAuthorizationFilter: "+memberEntity);
                PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
                //JWT 토큰 서명을 통해서 서명이 정상이면  Authentication 객체를 만든다
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

               // System.out.println(authentication.getAuthorities());
//강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
// return 사용자 명 보내기
                chain.doFilter(request, response);
            }

        } catch (SignatureException | MalformedJwtException e) { //서명 오류 or JWT 구조 문제
            ((HttpServletResponse) response).sendError(401, "SignatureException error");
        } catch (ExpiredJwtException e) {//유효 기간이 지난 JWT를 수신한 경우
            ((HttpServletResponse) response).sendError(401, "ExpiredJwtException error");
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

    }
}

