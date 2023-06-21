package city.olooe.plogging_project.service.community;

import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.dto.community.ReplyDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.community.ReplyEntity;
import city.olooe.plogging_project.persistence.community.ReplyRepository;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 댓글 Service
 */

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @param pageable
     * @return 해당글의 모든 댓글
     * @Brief 댓글 작성하기
     */
    public Page<ReplyDTO> writeReply(ApplicationUserPrincipal user, ReplyDTO replyDTO, Pageable pageable) {
        ReplyEntity replyEntity = replyRepository.save(
                ReplyEntity.builder()
                        .replyer(MemberEntity.builder().memberNo(user.getMember().getMemberNo()).build())
                        .reply(replyDTO.getReply())
                        .bno(BoardEntity.builder().bno(replyDTO.getBno()).build())
                        .build());
        Page<ReplyEntity> byBno = replyRepository.findByBno(replyEntity.getBno(), pageable);
        return byBno.map(ReplyDTO::new);
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @param pageable
     * @return 해당글의 모든 댓글
     * @Brief 해당글의 모든 댓글 조회하기
     */
    public Page<ReplyDTO> getReplysOfBoard(Long bno, Pageable pageable) {
        Page<ReplyEntity> byBno = replyRepository.findByBno(BoardEntity.builder().bno(bno).build(), pageable);
        return byBno.map(ReplyDTO::new);
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @return 삭제한 값
     * @Brief 댓글 삭제하기
     */
    public ReplyDTO deleteReply(ReplyDTO replyDTO){
        replyRepository.delete(ReplyEntity.builder().rno(replyDTO.getRno()).build());
        return replyDTO;
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @return 모든 댓글
     * @Brief 모든 댓글 조회하기
     */
    public List<ReplyDTO> getAll() {
        List<ReplyEntity> replyEntities = replyRepository.findAll();
        return replyEntities.stream().map(ReplyDTO::new).collect(Collectors.toList());
    }

}
