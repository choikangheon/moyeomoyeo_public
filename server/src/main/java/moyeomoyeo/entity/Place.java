package moyeomoyeo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moyeomoyeo.entity.idclass.FriendMultiId;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@SequenceGenerator(
        name = "PLACE_SEQ_GENERATOR",
        sequenceName = "PLACE_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)


@Table(name = "place")
@Data
@NoArgsConstructor
public class Place implements Serializable {

    @Id
    @Column(name = "PlaceCode")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACE_SEQ_GENERATOR")
    private Integer PlaceCode;


    @ManyToOne
    @JoinColumn(name = "memId", nullable = false)
    private Member memId;

    @Column(name = "str_address")
    private String str_address;

    @Column(name = "x_position")
    private String x_position;

    @Column(name = "y_position")
    private String y_position;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date create_date;

    @Column(name = "mainAddress_flag")
    private int flag = 0;

    @Builder
    public  Place(Member memId, String str_address, String x_position,
                  String y_position, Date create_date, int flag) {
        this.memId = memId;
        this.str_address = str_address;
        this. x_position = x_position;
        this. y_position = y_position;
        this.create_date = create_date;
        this.flag = flag;

    }

}
