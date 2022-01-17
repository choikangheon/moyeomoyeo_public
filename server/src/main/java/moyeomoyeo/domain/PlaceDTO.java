package moyeomoyeo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.Place;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
public class PlaceDTO {

    private Integer placeCode;
    private Member memId;
    private String str_address;
    private String x_position;
    private String y_position;
    private Date create_date;
    private int flag ;

    @Builder
    public PlaceDTO(Integer placeCode, Member memId, String str_address, String x_position,String y_position, Date create_date, int flag) {
        this.placeCode = placeCode;
        this.memId = memId;
        this.str_address = str_address;
        this.x_position = x_position;
        this.y_position = y_position;
        this.create_date = create_date;
        this.flag = flag;
    }

    public Place toEntity() {
        Place place = Place.builder()
                .memId(memId)
                .str_address(str_address)
                .x_position(x_position)
                .y_position(y_position)
                .build();
        return place;
    }


}
