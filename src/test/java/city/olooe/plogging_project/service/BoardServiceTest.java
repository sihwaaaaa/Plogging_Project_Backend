package city.olooe.plogging_project.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardServiceTest {

  @Autowired
  BoardService boardService;

  // @DisplayName("게시글 조회 테스트")
  // @Test
  // public String listBoard() {
  // List<BoardDTO> boardDTOList = boardService.getBoardList();

  // return boardDTOList.;

  // }
}
