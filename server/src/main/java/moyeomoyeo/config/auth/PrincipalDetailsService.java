package moyeomoyeo.config.auth;


import moyeomoyeo.entity.Member;
import moyeomoyeo.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public PrincipalDetailsService (MemberRepository memberRepository ){
        this.memberRepository = memberRepository;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService의 loadUserByUsername");
        Member memberEntity = memberRepository.findByMemId(username);
        System.out.println("memberEntity : " + memberEntity);
        return new PrincipalDetails(memberEntity);
    }
}
