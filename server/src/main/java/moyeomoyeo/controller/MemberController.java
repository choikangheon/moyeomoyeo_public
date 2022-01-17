package moyeomoyeo.controller;

import moyeomoyeo.config.auth.PrincipalDetails;
import moyeomoyeo.domain.MemberDTO;
import moyeomoyeo.repository.MemberRepository;
import moyeomoyeo.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    //의존성 생성자 주입
    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }


    @GetMapping("/api/member")
    public String member(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("controller : "+principalDetails);
         return "member";
    }

    @GetMapping("/api/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/api/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/member") // POST 방식으로 Member 접속시 JOIN
    public void accountMember(@RequestBody MemberDTO memberDTO) {
        memberService.joinMember(memberDTO);
    }

}
