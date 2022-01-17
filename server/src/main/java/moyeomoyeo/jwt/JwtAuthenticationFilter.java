package moyeomoyeo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import moyeomoyeo.config.auth.PrincipalDetails;
import moyeomoyeo.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//login 요청해서  username,password post 전송시 필터 동작

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;


    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
    this.tokenProvider =tokenProvider;
    }


    @Override // /login 요청시 로그인 시도를 위해서 실행 되는 함수
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication : 로그인 시도");


        // 1. username,password 받아서
        try {
          /*  BufferedReader br = request.getReader();
            String input = null;
            while((input = br.readLine()) != null){
                System.out.println(input);
            }*/
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            System.out.println("JwtAuthenticationFilter : " + member);

            //토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPw());

            System.out.println("토큰생성: " + authenticationToken);

            // PrincipalDetailService 의 loadUserByUsername 실행됨
            // db에 있는 username과 password가 일치한다
            Authentication authentication = //  로그인된 정보가 담김
                    authenticationManager.authenticate(authenticationToken);
            System.out.println("로그인정보 담김 : " + authentication);

            //로그인 되었다
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인완료됨:" + principalDetails);//로그인 정상적으로 됨
            System.out.println("===========================");
            return authentication;
            //authentication 객체가 session 영역에 저장됨
            //리턴의 이유 : 권한 관리를 security 가 대신 해주기 때문이다.
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("======================");

        // 2. 정상인지 로그인 시도를 해본다
        // PrincipoalDetailsService가 호출  loaduserByUsername() 함수 호출
        // 3. princiaplDetails를 세션에 담고 (이것을 해줘야지 권한 관리가 된다)
        // 4.JWT 토큰을 만들어서 응답하면된다
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 로그인 인증 완료");


        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = tokenProvider.doGenerateToken(principalDetails);

      /*  Date now = new Date();
        Claims claims = Jwts.claims();
        claims.put("memId", principalDetails.getMember().getMemId());
        claims.put("memPw", principalDetails.getMember().getMemId());
        claims.put("memName", principalDetails.getMember().getMemName());

        String jwtToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS512, secret)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();*/
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
