package moyeomoyeo.repository;

import moyeomoyeo.domain.MemberDTO;
import moyeomoyeo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Integer> {

    public Member findByMemId(String username);
    public Member findByMemName(String username);
}
