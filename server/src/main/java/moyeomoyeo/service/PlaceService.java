package moyeomoyeo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.config.auth.PrincipalDetails;
import moyeomoyeo.domain.Coordinate;
import moyeomoyeo.domain.MemberDTO;
import moyeomoyeo.domain.PlaceDTO;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.Place;
import moyeomoyeo.repository.MemberRepository;
import moyeomoyeo.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class PlaceService {
    private PlaceRepository placeRepository;
    private MemberRepository memberRepository;
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PlaceService(PlaceRepository placeRepository
            , MemberRepository memberRepository

    ) {
        this.placeRepository = placeRepository;
        this.memberRepository = memberRepository;


    }


    public String getPlaceList(String requestMemId) throws JsonProcessingException {
        String [] unitMemId = requestMemId.split(",");
        double total_x = 0;
        double total_y = 0;
        for(int i= 0 ; i < unitMemId.length;i++ ){
            Member member = memberRepository.findByMemName(unitMemId[i]);
            Place place = placeRepository.findBymemId(member);
            total_x += Double.parseDouble(place.getX_position());
            total_y += Double.parseDouble(place.getY_position());
        }

        double middle_x = total_x/unitMemId.length;
        double middle_y = total_y/unitMemId.length;
        Coordinate coordinate = new Coordinate();
        coordinate.setX(String.valueOf(middle_x));
        coordinate.setY(String.valueOf(middle_y));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(coordinate);
        return json;
    }

    public String searchByName() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String memerID;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        memerID = principalDetails.getUsername();

        Member memberDTO = memberRepository.findByMemId(memerID);
        Place placeDTO = placeRepository.findBymemId(memberDTO);

        Coordinate coordinate = new Coordinate();
        coordinate.setAddress_name(placeDTO.getStr_address());
        coordinate.setX(placeDTO.getX_position());
        coordinate.setY(placeDTO.getY_position());
        coordinate.setMember_name(placeDTO.getMemId().getMemName());

        String json = mapper.writeValueAsString(coordinate);
        return json;

    }

    public Coordinate convertGPS(String address) throws JsonProcessingException {

        Coordinate coordinate = new Coordinate();
        String url = "https://dapi.kakao.com/v2/local/search/address.json?";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 941495be79329274395ba40a4a339234");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", "{query}")
                .queryParam("analyze_type", "{analyze_type}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("query", address);
        params.put("analyze_type", "similar");

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        ResponseEntity<String> respEntity = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class, params);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(respEntity.getBody());
        coordinate.setX(jsonNode.get("documents").get(0).get("x").asText());
        coordinate.setY(jsonNode.get("documents").get(0).get("y").asText());
        coordinate.setMember_name(jsonNode.get("documents").get(0).get("address_name").asText());

        return coordinate;
    }

    public void insertPlace(String address) throws JsonProcessingException {

        String memerID;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        memerID = principalDetails.getUsername();

        Member member = memberRepository.findByMemId(memerID);


        Coordinate coordinate = new Coordinate();
        coordinate = convertGPS(address);


        PlaceDTO placeDTO = PlaceDTO.builder()
                .memId(member)
                .str_address(address)
                .x_position(coordinate.getX())
                .y_position(coordinate.getY())
                .build();

        placeRepository.save(placeDTO.toEntity());

    }


}

