package city.olooe.plogging_project.persistence;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.extern.slf4j.Slf4j;

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
   * @brief: 게시물 작성 테스트
   */
  @Test
  @DisplayName("게시글 입력 테스트")
  public void insetBoard() {
    BoardEntity boardEntity = BoardEntity.builder()
        .memberEntity(MemberEntity.builder().memberNo(1L).build())
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
   * @brief: 게시물 전체조회 테스트
   */
  @Test
  public void findByBno() {
    boardRepository.findBoardEntityByOrderByBnoDesc().forEach(board -> System.out.println(board));
  }

  /**
   * @author : 김성진
   * @date: '23.06.06
   * 
   * @param:
   * @return:
   * 
   * @brief: 게시물 수정 테스트
   */
  @Test
  public void updateBoard() {

  }
}
