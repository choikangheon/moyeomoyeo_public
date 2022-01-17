package moyeomoyeo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moyeomoyeo.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/api/member/place/multiple/{requestMemId}")
    public String getPlaceMultiple(@PathVariable(value = "requestMemId") String requestMemId) throws JsonProcessingException {
        log.info(requestMemId.toString());
        return placeService.getPlaceList(requestMemId);
    }


    @GetMapping("/api/member/place")
    public String getPlace() throws JsonProcessingException {
        log.info("placeController : getPlace");
        return placeService.searchByName();
    }

    @PostMapping("/api/member/place")
    public void insertPlace(@RequestBody String address) throws JsonProcessingException {
        log.info("inserPlace_placeController");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(address);

        String finalAddress = jsonNode.get("address").asText();
        placeService.insertPlace(finalAddress);

    }
}