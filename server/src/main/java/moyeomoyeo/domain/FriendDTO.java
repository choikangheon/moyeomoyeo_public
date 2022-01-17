package moyeomoyeo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import moyeomoyeo.entity.Friend;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.idclass.FriendMultiId;


import java.util.Date;

@Getter
@Setter

public class FriendDTO {

    private Member member;
    private String friend_respondent;
    private Date allow_date;

    @Builder
    public FriendDTO(Member member, String friend_respondent, Date allow_date) {
        this.member = member;
        this.friend_respondent = friend_respondent;
        this.allow_date = allow_date;
    }

    public Friend toEntity() {
        Friend friend = Friend.builder()
                .member(member)
                .friend_respondent(friend_respondent)
                .allow_date(allow_date)
                .build();

        return friend;
    }
}