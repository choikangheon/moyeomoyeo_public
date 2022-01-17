package moyeomoyeo;

import moyeomoyeo.domain.FriendDTO;
import moyeomoyeo.domain.MemberDTO;
import moyeomoyeo.entity.Friend;
import moyeomoyeo.entity.Member;
import moyeomoyeo.entity.idclass.FriendMultiId;
import moyeomoyeo.repository.FriendRepository;
import moyeomoyeo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@SpringBootTest
@Rollback(value = false)
class memberInsertTest{

	@PersistenceContext
	EntityManager entityManager;


	@Autowired
	MemberRepository memberRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	FriendRepository friendRepository;

	@Test
	void insert_Test(){
		String rawPassword = "test";
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		MemberDTO memberDTO = MemberDTO.builder()
				.memId("test5")
				.memPw(encPassword)
				.memName("김수진")
				.memBirth("1987-12-31")
				.memPhone("010-5451-5452")
				.authority("ROLE_USER")
				.build();


		memberRepository.save(memberDTO.toEntity());


	}


}