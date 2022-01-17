package moyeomoyeo.controller;

import moyeomoyeo.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/api/member/username")
    public String currentUserName(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("controller : "+principalDetails);
        System.out.println(principalDetails.getUsername());
        return "member";
    }

}
