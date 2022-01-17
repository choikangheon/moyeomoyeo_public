package moyeomoyeo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@NoArgsConstructor
@ToString

public class Member implements Serializable {

    @Column(name = "memCode")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Integer memCode;

    @Id
    @Column(name = "memId", nullable = false, length = 10)
    private String memId;

    @Column(name = "memPw", nullable = false, length = 100)
    private String memPw;

    @Column(name = "memName", nullable = false, length = 20)
    private String memName;

    @Column(name = "memBirth", nullable = false, length = 10)
    private String memBirth;

    @Column(name = "memPhone", nullable = false, length = 13)
    private String memPhone;

    @Column(name = "memAuthority", nullable = false, length = 13)
    private String authority;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date creatDate;

    public List<String> getRoleList() {
        if (this.authority.length() > 0) {
            return Arrays.asList(this.authority.split(","));
        }
        return new ArrayList<>();
    }

    @Builder
    public Member(Integer memCode, String memId, String memPw, String memName,
                  String memBirth, String memPhone, String authority, Date creatDate) {
        this.memId = memId;
        this.memPw = memPw;
        this.memName = memName;
        this.memBirth = memBirth;
        this.memPhone = memPhone;
        this.authority = authority;
        this.creatDate = creatDate;
    }


}
