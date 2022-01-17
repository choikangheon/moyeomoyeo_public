package moyeomoyeo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.config.auth.PrincipalDetails;
import moyeomoyeo.domain.FriendDTO;
import moyeomoyeo.entity.Friend;
import moyeomoyeo.repository.FriendRepository;
import moyeomoyeo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FriendService {
    /*
     * api path : localhost:8080/api/member/friend
     *
     * post : localhost:8080/api/member/friend/{resp_name}
     *
     * */

    private MemberRepository memberRepository;
    private FriendRepository friendRepository;


    @Autowired
    public FriendService(MemberRepository memberRepository, FriendRepository friendRepository) {
        this.memberRepository = memberRepository;
        this.friendRepository = friendRepository;
    }

    public void setPatchFriend(String req_id) {
        log.info("실행됨");
        String memerID;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        memerID = principalDetails.getUsername();

        int flag = friendRepository.patchFriend(memerID, req_id);
        log.info(String.valueOf(flag));


    }

    public String getFriendRequestList() {
        String req_name;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        req_name = principalDetails.getUsername();

        List<String> list = friendRepository.selectRequestFriend(req_name);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(list);
            log.debug(json);
            return json;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<String> getFriendList() {
        String req_name;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        req_name = principalDetails.getUsername();

        List<Friend> list = friendRepository.selectFriend(req_name);
        List<String> result_list = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getMember().getMemId().equals(req_name) != true) {
                result_list.add(list.get(i).getMember().getMemId());
            }
            if (list.get(i).getFriend_respondent().equals(req_name) != true)
                result_list.add(list.get(i).getFriend_respondent());
        }

        return result_list;
    }

    public void insertFrined(String reps_name) { // friend add
        String req_name;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        req_name = principalDetails.getUsername();

        FriendDTO friendDTO = FriendDTO.builder()
                .member(memberRepository.findByMemId(req_name))
                .friend_respondent(reps_name)
                .build();

        friendRepository.save(friendDTO.toEntity());
    }
}
