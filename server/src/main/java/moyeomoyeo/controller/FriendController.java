package moyeomoyeo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FriendController {


    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }


    @PatchMapping("/api/member/friend/{username}")
    public void patchFriend(@PathVariable("username") String resp_name) {
        friendService.setPatchFriend(resp_name);
    }

    @GetMapping("/api/member/friend/request")
    public @ResponseBody
    String getRequestList() {
        return friendService.getFriendRequestList();
    }

    @GetMapping("/api/member/friend")
    public @ResponseBody
    String getFriendList() { // 로그인된 username을 토대로 친구목록 가져옴
        List<String> list = friendService.getFriendList();
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

    @PostMapping("/api/member/friend/{username}")
    public void request_frined(@PathVariable("username") String resp_name) { // 친구추가

        friendService.insertFrined(resp_name);
    }

}
