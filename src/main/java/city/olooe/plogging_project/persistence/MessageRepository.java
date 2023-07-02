package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.friend.MessageEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    Page<MessageEntity> findByRoomNoOrderByMsgNoDesc(MessageRoomEntity entity, Pageable pageable);
}
