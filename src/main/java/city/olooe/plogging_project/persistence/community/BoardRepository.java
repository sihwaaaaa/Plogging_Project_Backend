package city.olooe.plogging_project.persistence.community;

import java.util.List;

import city.olooe.plogging_project.model.community.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.community.BoardEntity;

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
  Page<BoardEntity> findBoardEntityByOrderByBnoDesc(Pageable pageable);

  /**
   * @Author 김성진
   * @Date 23.06.08
   * @param bno
   * @return BoardEntity
   * @Brief 해당 게시글 번호에 따른 조회
   */
  BoardEntity findByBno(Long bno);

  /**
   * @Author 천은경
   * @Date 23.06.20
   * @param category
   * @param pageable
   * @return 카테고리별 게시글 조회
   * @Brief 카테고리별 게시글 페이징 조회하기
   */
  Page<BoardEntity> findByCategory(BoardCategory category, Pageable pageable);
  
}
