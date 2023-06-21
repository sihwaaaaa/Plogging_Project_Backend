package city.olooe.plogging_project.service;

import city.olooe.plogging_project.service.community.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.05
 * 
 * @brief : 게시글 관련 Service test
 */

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
