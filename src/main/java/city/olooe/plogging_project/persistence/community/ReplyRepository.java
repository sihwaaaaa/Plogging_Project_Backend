package city.olooe.plogging_project.persistence.community;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.community.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 댓글 Repository
 */

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    Page<ReplyEntity> findByBno(BoardEntity boardEntity, Pageable pageable);

    List<ReplyEntity> findByReplyer(MemberEntity memberEntity);
}
