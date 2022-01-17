
package moyeomoyeo.repository;

import moyeomoyeo.entity.Friend;
import moyeomoyeo.entity.idclass.FriendMultiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface FriendRepository extends JpaRepository<Friend, FriendMultiId> {
    @Override
    Optional<Friend> findById(FriendMultiId friendMultiId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE FRIEND SET ALLOW_DATE= SYSDATE " +
            " where  mem_id = :request_Id and friend_respondent = :member  ", nativeQuery = true)
    public int patchFriend(@Param("member") String memId, @Param("request_Id") String request_Id);


    @Transactional
    @Query(value = "select mem_id from Friend " +
            "where friend_respondent = :member " +
            "and ALLOW_DATE is null", nativeQuery = true)
    public List<String> selectRequestFriend(@Param("member") String memId);

    @Transactional
    @Query(value = "select * " +
            "from Friend where (mem_Id = :member " +
            "or friend_respondent = :member) and ALLOW_DATE is not null", nativeQuery = true)
    public List<Friend> selectFriend(@Param("member") String memId);
}

