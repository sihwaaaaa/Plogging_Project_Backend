package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.RoomMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMemberEntity, Long> {
    @Query("select rm from RoomMemberEntity rm join RoomMemberEntity rm2 " +
            "on rm.roomNo.roomNo = rm2.roomNo.roomNo " +
            "where rm.memberNo.memberNo = :memberNo " +
            "and rm2.memberNo.memberNo = :friendNo")
    Optional<RoomMemberEntity> findRoom(@Param("memberNo") Long memberNo,
                                          @Param("friendNo") Long friendNo);
}
