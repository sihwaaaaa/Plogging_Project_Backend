package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.05
 * 
 * @brief : 게시글 관련 Repository test
 */


@EqualsAndHashCode
@ToString
@SpringBootTest
@Slf4j
public class BoardRepositoryTest {
  @Autowired
  BoardRepository boardRepository;

  @Autowired
  MemberRepository memberRepository;

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시글 작성 테스트
   */
  @Test
  @DisplayName("게시글 입력 테스트")
  public void insertBoard() {
    BoardEntity boardEntity = BoardEntity.builder()
        .memberEntity(MemberEntity.builder().memberNo(6L).build())
        .title("test")
        .content("test입니다.")
        .category(0)
        .build();
    boardRepository.save(boardEntity);

  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시글 전체조회 테스트
   */
  @Test
  @DisplayName("게시글 전체조회")
  public void findAll() {
    boardRepository.findBoardEntityByOrderByBnoDesc().forEach(board -> System.out.println(board));
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시글 단일조회 테스트
   */
  @Test
  @DisplayName("게시글 단일 조회")
  public void findByBno() {
    log.info("{}", boardRepository.findByBno(1L));
  }

  /**
   * @author : 김성진
   * @date: '23.06.06
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시글 수정 테스트
   */
  @Test
  @DisplayName("게시글 수정 테스트")
  public void updateBoard() {
    BoardEntity boardEntity = boardRepository.findByBno(9L);
    boardEntity.update("수정된 제목2", "수정된 내용2", new Date());
    log.info("{}", boardRepository.save(boardEntity));
  }

  /**
   * @author : 김성진
   * @date: '23.06.06
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시글 삭제 테스트
   */
  @Test
  @DisplayName("게시글 삭제 테스트")
  public void deleteBoard() {
    BoardEntity boardEntity = boardRepository.findByBno(2L);
    boardRepository.delete(boardEntity);
  }

}
