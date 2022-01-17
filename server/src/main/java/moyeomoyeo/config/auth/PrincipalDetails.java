package moyeomoyeo.config.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import moyeomoyeo.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails (Member member){
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        member.getRoleList().forEach(r->{
            authorities.add(()->r); //
            System.out.println("PrincipalDetails : " + authorities);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getMemPw();
    }

    @Override // pk 값
    public String getUsername() {
        return member.getMemId();
    }

    @Override //계정 만료 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정 잠김여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호 만료 여부 true 만료안됨
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
