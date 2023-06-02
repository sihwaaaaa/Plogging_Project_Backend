package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.BoardEntity;

/**
 * @author: 김성진
 * @date: 2023.06.02
 * @brief: BoardEntity의 jpa 인터페이스
 */

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
