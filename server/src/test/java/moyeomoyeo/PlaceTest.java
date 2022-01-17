package moyeomoyeo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.domain.Coordinate;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.Place;
import moyeomoyeo.repository.MemberRepository;
import moyeomoyeo.repository.PlaceRepository;
import moyeomoyeo.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Transactional
@SpringBootTest
@Slf4j
@Rollback(value = false)
public class PlaceTest {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceService placeService;

    @Test
    void searchByName() {
        Member member = memberRepository.findByMemId("test");
        Place placeDTO = placeRepository.findBymemId(member);
        System.out.println(placeDTO.toString());
    }

    @Test
    public void getMemberMiddlePlace(){
        String memberList = "최강헌,이재호,신준수";

    }


@Test
public void searchMem_name(){
       /*List <Member> member = memberRepository.findByMemName("김수진");*/
}

    @Test
    public void convertGPS() throws JsonProcessingException {

        String url = "https://dapi.kakao.com/v2/local/search/address.json?";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 941495be79329274395ba40a4a339234");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query","{query}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("query","인천시부평구평천로305번길");



        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        ResponseEntity<String> respEntity = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class,params);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(respEntity.getBody());
        String test_x  = jsonNode.get("documents").get(0).get("x").asText();
        System.out.println(test_x);
        }




    @Test
    void insertPlace() {
        Member member = memberRepository.findByMemId("test");

        String str_address = "인천시 부평구 평천로305번길 15";
        String gps_address = null;

        Place place = Place.builder()
                .memId(member)
                .str_address(str_address)
                .x_position(null)
                .y_position(null)
                .build();
        placeRepository.save(place);
    }
}
