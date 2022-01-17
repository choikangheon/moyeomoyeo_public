package moyeomoyeo.entity;

import lombok.*;
import moyeomoyeo.entity.idclass.FriendMultiId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "friend")
@NoArgsConstructor
@IdClass(FriendMultiId.class)
@Getter
@Setter
@ToString
public class Friend implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(name = "memId", nullable = false)
    /*@ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_memId", nullable = false)*/
    private Member member;


    @Id
    private String friend_respondent;


    @Column(name = "allow_date")
    private Date allow_date;



    @Builder
    public Friend(Member member, String friend_respondent, Date allow_date) {
        this.member = member;
        this.friend_respondent = friend_respondent;
        this.allow_date = allow_date;
    }
}