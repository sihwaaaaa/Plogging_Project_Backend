package city.olooe.plogging_project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.BoardEntity;

/**
 * @author: 김성진
 * @date: 2023.06.02
 * @brief: BoardEntity의 jpa 인터페이스
 *         인터페이스를 생성 후 JpaRepository<Entity 클래스, PK 타입>을 상속하면
 *         기본적인 CRUD 메소드가 자동으로 생성된다.
 */

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  // 23.06.05 추가
  List<BoardEntity> findBoardEntityByOrderByBnoDesc();
}
