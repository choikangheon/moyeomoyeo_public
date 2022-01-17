package moyeomoyeo.domain;

import lombok.*;
import moyeomoyeo.entity.Authority;
import moyeomoyeo.entity.Member;


import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter


public class MemberDTO {

    private Integer memCode;
    private String memId;
    private String memPw;
    private String memName;
    private String memBirth;
    private String memPhone;
    private String authority;
    private Date createDate;


    public Member toEntity() {
        Member member = Member.builder()
                .memId(memId)
                .memPw(memPw)
                .memName(memName)
                .memBirth(memBirth)
                .memPhone(memPhone)
                .authority(authority)
                .build();
        return member;
    }

    @Builder
    public MemberDTO(Integer memCode, String memId, String memPw, String memName,
                     String memBirth, String memPhone,String authority, Date createdDate) {
        this.memCode = memCode;
        this.memId = memId;
        this.memPw = memPw;
        this.memName = memName;
        this.memBirth = memBirth;
        this.memPhone = memPhone;
        this.authority =authority;
        this.createDate = createDate;
    }
}