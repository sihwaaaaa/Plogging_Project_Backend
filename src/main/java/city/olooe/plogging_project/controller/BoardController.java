package city.olooe.plogging_project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.dto.BoardDTO;

import city.olooe.plogging_project.service.BoardService;
import lombok.RequiredArgsConstructor;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 controller
 */

@RequiredArgsConstructor
@RestController
public class BoardController {

  private final BoardService boardService;

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardCreateDTO
   * @return: Long
   * 
   * @brief: 게시물 작성
   */
  @PostMapping("/board")
  public Long create(@RequestBody BoardDTO boardCreateDTO) {
    return boardService.create(boardCreateDTO);
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardUpdateDTO
   * @return: Long
   * 
   * @brief: 게시물 수정
   */
  @PutMapping("/board/{bno}")
  public Long update(@PathVariable Long bno, @RequestBody BoardDTO boardUpateDTO) {
    return boardService.update(bno, boardUpateDTO);
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: bno
   * @return: Long
   * 
   * @brief: 게시물 개별 조회
   */
  @GetMapping("/board/{bno}")
  public BoardDTO searchByBno(@PathVariable Long bno) {
    return boardService.searchByBno(bno);
  }

  // 전체조회
  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: -
   * @return: List
   * 
   * @brief: 게시물 전체 조회
   */
  @GetMapping("/board")
  public List<BoardDTO> searchAllBoard() {
    return boardService.searchAllBoard();
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: bno
   * @return: -
   * 
   * @brief: 게시물 삭제
   */
  @DeleteMapping("/board/{bno}")
  public void delete(@PathVariable Long bno) {
    boardService.delete(bno);
  }

}
