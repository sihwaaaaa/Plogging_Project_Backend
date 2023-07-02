package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.FriendEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRoomRepository extends JpaRepository<MessageRoomEntity, Long> {

    @Query("select r from MessageRoomEntity r left join r.roomMembers m where m.memberNo = :memberNo order by r.roomNo desc")
    List<MessageRoomEntity> findByMember(MemberEntity memberNo);


}
