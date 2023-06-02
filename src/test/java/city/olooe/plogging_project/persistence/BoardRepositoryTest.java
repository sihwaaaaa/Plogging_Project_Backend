package city.olooe.plogging_project.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.BoardEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardRepositoryTest {
  @Autowired
  BoardRepository boardRepository;

  @DisplayName("게시글 생성 테스트")
  @Test
  public void createBoard() {
    BoardEntity board = BoardEntity.builder().writer(1L).title("게시글 1").content("게시글 1번입니다.").category(0)
        .build();

    log.info("{}", boardRepository.save(board));
  }

  @DisplayName("게시글 조회 테스트")
  @Test
  public void listBoard() {
    log.info("메세지 : ", boardRepository.findAll());
  }
}
