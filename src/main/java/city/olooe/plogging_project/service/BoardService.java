package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.persistence.BoardRepository;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 서비스
 */

@Service
public class BoardService {
  private BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @Transactional
  public Long boardPost(BoardDTO boardDTO) {
    return boardRepository.save(boardDTO.boardEntity()).getBno();
  }

  @Transactional
  public List<BoardDTO> getBoardList() {
    List<BoardEntity> boardList = boardRepository.findAll();
    List<BoardDTO> boardDTOList = new ArrayList<>();

    for (BoardEntity boardEntity : boardList) {
      BoardDTO boardDTO = BoardDTO.builder().bno(null).writer(null).title(null).content(null).regDate(null)
          .category(null).build();
      boardDTOList.add(boardDTO);
    }

    return boardDTOList;
  }
}
