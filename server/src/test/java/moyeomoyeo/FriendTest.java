package moyeomoyeo;

import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.domain.FriendDTO;
import moyeomoyeo.entity.Friend;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.idclass.FriendMultiId;
import moyeomoyeo.repository.FriendRepository;
import moyeomoyeo.repository.MemberRepository;
import moyeomoyeo.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@Rollback(value = false)
@Slf4j
public class FriendTest {
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    FriendService friendService;

    @Test
    public void patchFrined(){
        int flag = friendRepository.patchFriend("test4","test");
        System.out.println(flag);
    }

    @Test
    public void getRequestFrined(){
    List<String> list = friendRepository.selectRequestFriend("최강헌");
        for(String a : list)
            System.out.println(a);
    }

    @Test
    void friend_insert_test() {
        Member member = memberRepository.findByMemId("test4");
        FriendDTO friendDTO = FriendDTO.builder()
                .member(member)
                .friend_respondent("test")
                .build();

        friendRepository.save(friendDTO.toEntity());

    }

    @Test
    public void selectFriend() {

    }
}
