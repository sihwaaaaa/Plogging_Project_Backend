package city.olooe.plogging_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 서비스
 */

@RequiredArgsConstructor // final이 선언된 필드를 인자값으로 하는 생성자 대신 생성
@Service
public class BoardService {
  private final BoardRepository boardRepository;

  // @RequriedArgsConstructor가 있으면 필요없음
  // public BoardService(BoardRepository boardRepository) {
  // this.boardRepository = boardRepository;
  // }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardCreateDTO
   * @return: boardReposity
   * 
   * @brief: 게시물 작성 서비스
   */
  @Transactional
  public Long create(BoardDTO boardCreateDTO) {
    return boardRepository.save(boardCreateDTO.toEntity()).getBno();
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: boardUpadteDTO
   * @return: bno
   * 
   * @brief: 게시물 수정 서비스
   */
  @Transactional
  public Long update(Long bno, BoardDTO boardUpdateDTO) {
    BoardEntity boardEntity = boardRepository.findById(bno)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

    boardEntity.update(boardUpdateDTO.getTitle(), boardUpdateDTO.getContent());

    return bno;
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: bno
   * @return: boardDTO
   * 
   * @brief: 게시물 개별조회 서비스
   */
  @Transactional(readOnly = true)
  public BoardDTO searchByBno(Long bno) {
    BoardEntity boardEntity = boardRepository.findById(bno)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

    return new BoardDTO(boardEntity);
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: -
   * @return: List
   * 
   * @brief: 게시물 전체조회 서비스
   */
  @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 기능을 조회로 제한
  public List<BoardDTO> searchAllBoard() {
    return boardRepository.findBoardEntityByOrderByBnoDesc().stream() // boardRepository 결과로 넘어온 BoardEntity의 Stream을
        .map(BoardDTO::new) // map을 통해 BoardListResponseDTO로 변환
        .collect(Collectors.toList()); // List로 반환
  }

  /**
   * @author : 김성진
   * @date: '23.06.05
   * 
   * @param: bno
   * @return: -
   * 
   * @brief: 게시물 삭제 서비스
   */
  @Transactional
  public void delete(Long bno) {
    BoardEntity boardEntity = boardRepository.findById(bno)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다."));

    boardRepository.delete(boardEntity);
  }

}
