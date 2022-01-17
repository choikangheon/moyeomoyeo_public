package moyeomoyeo.entity.idclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import moyeomoyeo.entity.Member;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@Embeddable
@EqualsAndHashCode
public class FriendMultiId implements Serializable {

    public FriendMultiId(String member, String friend_respondent){
        this.member = member;
        this.friend_respondent = friend_respondent;
    }
    private String member;
    private String friend_respondent;
  /*  @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name="memId")*/
  /*@Column(name = "memId", nullable = false, length = 10)
    private String member;

    @Column(name = "friend_respondent", nullable = false, length = 10)
    private String friend_respondent;*/

    public FriendMultiId() {

    }
}
