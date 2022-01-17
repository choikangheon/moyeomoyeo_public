package moyeomoyeo.service;

import moyeomoyeo.domain.MemberDTO;
import moyeomoyeo.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




    @Transactional
    public String joinMember(MemberDTO memberDto) {
    String rawPassword = memberDto.getMemPw();
        memberDto.setMemPw(bCryptPasswordEncoder.encode(rawPassword));
        memberDto.setAuthority("ROLE_USER"); //  ROLE_USER or ROLE_ADMIN
        return memberRepository.save(memberDto.toEntity()).getMemName();
    }

}
