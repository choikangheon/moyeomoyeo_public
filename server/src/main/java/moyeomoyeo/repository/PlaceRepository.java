package moyeomoyeo.repository;

import moyeomoyeo.domain.PlaceDTO;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    public Place findBymemId(Member member);
}
