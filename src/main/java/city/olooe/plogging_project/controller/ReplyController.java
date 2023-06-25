package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.dto.community.ReplyDTO;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.community.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 댓글 controller
 */

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("reply")
public class ReplyController {
    private final ReplyService replyService;

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @return 모든 댓글 리스트
     * @Brief 모든 댓글 조회
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<ReplyDTO> replyAll = replyService.getAll();
        return ResponseEntity.ok().body(ResponseDTO.builder().data(replyAll).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param user
     * @param replyDTO
     * @param pageable
     * @return 해당글의 모든 댓글
     * @Brief 댓글 작성하기
     */
    @PostMapping("/write")
    public ResponseEntity<?> writeReply(@AuthenticationPrincipal ApplicationUserPrincipal user,
                                        @RequestBody ReplyDTO replyDTO,
                                        @PageableDefault(sort = "rno", size = 5,
                                                direction = Sort.Direction.DESC) Pageable pageable){
        Page<ReplyDTO> replyDTOS = replyService.writeReply(user, replyDTO, pageable);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(replyDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @return 삭제한 댓글
     * @Brief 댓글 삭제하기
     */
    @DeleteMapping("/delete/{rno}")
    public ResponseEntity<?> deleteReply(@PathVariable Long rno) {
        replyService.deleteReply(rno);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(rno).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @param pageable
     * @return 해당글의 댓글 리스트
     * @Brief 해당글의 모든 댓글 조회하기
     */
    @GetMapping("/{bno}")
    public ResponseEntity<?> getReplys(@PathVariable Long bno, @PageableDefault(sort = "rno", size = 5
            , direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ReplyDTO> replysOfBoard = replyService.getReplysOfBoard(bno, pageable);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(replysOfBoard).build());
    }

}
