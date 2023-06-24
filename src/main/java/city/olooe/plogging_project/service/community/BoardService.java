package city.olooe.plogging_project.service.community;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.community.BoardCategory;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.persistence.community.BoardRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author : 김성진
 * @date : 2023.06.02
 * @brief : 게시글 관련 서비스
 *
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 게시글 service 보완
 */

@RequiredArgsConstructor // final이 선언된 필드를 인자값으로 하는 생성자 대신 생성
@Service
@Slf4j
public class BoardService {
  private final BoardRepository boardRepository;


  /**
   * @Author 천은경
   * @Date 23.06.20
   * @param user
   * @param boardCreateDTO
   * @return 작성한 게시글
   * @Brief 게시글 작성하기 (일반 커뮤니티 작성 or 플로깅으로 작성)
   */
  @Transactional
  public BoardDTO create(ApplicationUserPrincipal user, BoardDTO boardCreateDTO) {
    BoardEntity boardEntity = BoardEntity.builder()
            .memberNo(MemberEntity.builder().memberNo(user.getMember().getMemberNo()).build())
            .title(boardCreateDTO.getTitle())
            .content(boardCreateDTO.getContent())
            .category(BoardCategory.COMMUNITY)
            .build();

    if (boardCreateDTO.getPloggingNo() != null){
      boardEntity.setCategory(BoardCategory.PLOGGING);
      boardEntity.setPloggingNo(PloggingEntity.builder().ploggingNo(boardCreateDTO.getPloggingNo()).build());
      BoardDTO boardDTO = new BoardDTO(boardRepository.save(boardEntity));
      boardDTO.setPloggingNo(boardCreateDTO.getPloggingNo());
      return boardDTO;
    }     

    return new BoardDTO(boardRepository.save(boardEntity));
  }


  /**
   * @author : 김성진
   * @date: '23.06.05
   * @param: boardUpadteDTO
   * @return: bno
   * @brief: 게시물 수정 서비스
   */
  @Transactional
  public BoardDTO update(BoardDTO boardUpdateDTO) {
    BoardEntity boardEntity = boardRepository.findById(boardUpdateDTO.getBno())
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

    boardEntity.update(boardUpdateDTO.getTitle(), boardUpdateDTO.getContent(), new Date());

    return boardUpdateDTO;
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
    BoardDTO boardDTO = new BoardDTO(boardEntity);
    boardDTO.setReplyCount(boardEntity.getReplys().size());
    if(boardEntity.getPloggingNo() != null){
      boardDTO.setPloggingNo(boardEntity.getPloggingNo().getPloggingNo());
    }
    return boardDTO;
  }

  /**
   * @Author 천은경
   * @Date 23.06.21
   * @param category
   * @param pageable
   * @return 카테고리별 게시글
   * @Brief 카테고리별 게시글 페이징 조회
   */
  @Transactional(readOnly = true)
  public Page<BoardDTO> BoardOfCategory(String category, Pageable pageable) {
    Page<BoardEntity> byCategory = boardRepository.findByCategory(BoardCategory.valueOf(category), pageable);
    return byCategory.map(BoardDTO::new);
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
  @Transactional(readOnly = true)
  public Page<BoardDTO> searchAllBoard(Pageable pageable) {
    return boardRepository.findBoardEntityByOrderByBnoDesc(pageable).map(BoardDTO::new);
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
