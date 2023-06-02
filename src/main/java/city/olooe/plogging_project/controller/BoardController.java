package city.olooe.plogging_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.service.BoardService;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 controller
 */

@Controller
public class BoardController {

  private BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/board")
  public String list(Model model) {
    List<BoardDTO> boardDTOList = boardService.getBoardList();
    model.addAttribute("postList", boardDTOList);
    return "hello"; // 차후 경로 지정
  }

  @GetMapping("/post")
  public String post() {
    return "world"; // 차후 경로 지정
  }

  // @PostMapping("/post")
  // public String writeBoard(BoardDTO boardDTO) {
  // BoardService.
  // return
  // }
}
