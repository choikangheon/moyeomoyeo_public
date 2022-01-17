package moyeomoyeo.config;

import lombok.RequiredArgsConstructor;
import moyeomoyeo.jwt.JwtAuthenticationFilter;
import moyeomoyeo.jwt.JwtAuthorizationFilter;
import moyeomoyeo.jwt.TokenProvider;
import moyeomoyeo.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CorsFilter corsFilter;
    private final   TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     //   http.addFilterBefore(new Filter_first(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) //@CrossOrigin 인증(x) 컨트롤러에, 인증이 필요할때는 securityConfig에 필터 사용해야함
                .formLogin().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),tokenProvider)) // AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),memberRepository))
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/member/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

    }
}