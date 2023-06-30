package city.olooe.plogging_project.service.community;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import city.olooe.plogging_project.dto.AttachDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.AttachEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.model.community.BoardCategory;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.AttachRepository;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
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

  private final AttachRepository attachRepository;
  private final PointHistoryRepository pointHistoryRepository;


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
    BoardEntity boardEntity = boardCreateDTO.toEntity(user, boardCreateDTO);

    if (boardCreateDTO.getPloggingNo() != null) {
      boardEntity.setCategory(BoardCategory.PLOGGING);

      // 플로깅 인증글 작성 후 포인트 지급
      PointHistoryEntity historyEntity = PointHistoryEntity.builder()
              .memberNo(user.getMember())
              .type(RewardTypeStatus.Plogging)
              .point(RewardTypeStatus.Plogging.getValue())
              .build();

      pointHistoryRepository.save(historyEntity);
    }

    boardRepository.save(boardEntity);

    if(boardCreateDTO.getAttach() != null){
      AttachDTO attach = boardCreateDTO.getAttach();
      attach.setBno(boardEntity.getBno());
      attachRepository.save(attach.toEntity());
    }

    return boardCreateDTO;
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
    // 게시글 유무에 따른 예외처리
    BoardEntity boardEntity = boardRepository.findById(boardUpdateDTO.getBno())
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    
    // 첨부파일 존재 여부 검사 후, 해당글의 첨부파일 수정
    Optional<AttachEntity> attachEntity = attachRepository.findByBno(boardEntity);
    AttachDTO attach = boardUpdateDTO.getAttach();
    attachEntity.ifPresent(entity ->
            entity.updateAttach(attach.getUuid(), attach.getPath(), attach.getFilename()));

    // 게시글 업데이트
    boardEntity.update(boardUpdateDTO.getTitle(), boardUpdateDTO.getContent(), new Date());
    BoardDTO boardDTO = new BoardDTO(boardEntity);

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
  public ResponseDTO<?> searchByBno(Long bno) {
    Optional<BoardEntity> boardEntity = boardRepository.findById(bno);

    if(boardEntity.isPresent()){
      BoardDTO boardDTO = new BoardDTO(boardEntity.get());

      boardDTO.setReplyCount(boardEntity.get().getReplys().size());

      // 첨부파일 유무 검사
      Optional<AttachEntity> byBno = attachRepository.findByBno(boardEntity.get());
      byBno.ifPresent(attachEntity -> boardDTO.setAttach(new AttachDTO(attachEntity)));

      if(boardEntity.get().getPloggingNo() != null){
        boardDTO.setPloggingNo(boardEntity.get().getPloggingNo().getPloggingNo());
      }
      return ResponseDTO.builder().data(boardDTO).build();
    }
    return ResponseDTO.builder().error("500").build();
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
