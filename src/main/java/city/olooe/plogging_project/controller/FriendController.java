package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.friend.FriendDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.friend.FriendEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.FriendService;
import city.olooe.plogging_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("friend")
@RequiredArgsConstructor
@Slf4j
public class FriendController {

    private final FriendService friendService;
    private final MemberService memberService;

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param user
     * @param status
     * @return List<FriendDTO> 나의 팔로잉 상태별 플친 리스트
     * @Brief 팔로잉 상태별 플친 리스트 api
     */
    @GetMapping("/fromMe/{status}")
    public ResponseEntity<?> myFriendList(@AuthenticationPrincipal ApplicationUserPrincipal user, @PathVariable String status) {

        List<FriendEntity> friendEntities = friendService.GetMyFriendList(user.getMember().getMemberNo(), status);

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.11
     * @param user
     * @param status
     * @return 팔로워 상태별 플친 리스트
     * @Breif 팔로워 상태별 플친 리스트 api
     */
    @GetMapping("/toMe/{status}")
    public ResponseEntity<?> friendListToMe(@AuthenticationPrincipal ApplicationUserPrincipal user, @PathVariable String status) {
    //ResponseEntity<ResponseDTO<List<FriendDTO>>>

        List<FriendEntity> friendEntities = friendService.getFriendListToMe(user.getMember().getMemberNo(), status);

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.06
     * @return 플친 리스트
     * @Brief 플친 전체 조회
     */
    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        List<FriendEntity> friendEntities = friendService.allFriend();
        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.11
     * @param user
     * @param friendDTO
     * @return 나의 플친 요청 리스트
     * @Brief 플친 신청하기
     */
    @PostMapping("request")
    public ResponseEntity<?> requestFriend(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody FriendDTO friendDTO) {

        List<FriendEntity> friendEntities = friendService.requestFriend(user.getMember().getMemberNo(), friendDTO.getToMemberNo());
        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.13
     * @param user
     * @param toMember
     * @return 보낸 요청 리스트 or 차단 리스트
     * @Breif 플친 요청 취소 or 차단 취소
     */
    @DeleteMapping("cancel")
    public ResponseEntity<?> cancelRequest(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody FriendDTO toMember) {
        List<FriendEntity> friendEntities = friendService.cancelRequest(user.getMember().getMemberNo(), toMember.getToMemberNo());
        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.11
     * @param user
     * @param fromMember
     * @return 나에게 온 플친 요청 리스트
     * @Breif 플친 수락하기
     */
    @PutMapping("accept")
    public ResponseEntity<?> acceptFriend(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody FriendDTO fromMember){
        log.warn("플친 수락 데이터 확인 : {}", fromMember.getFromMemberNo());
        List<FriendEntity> friendEntities = friendService.acceptRequest(user.getMember().getMemberNo(), fromMember.getFromMemberNo());
        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.12
     * @param user
     * @param fromMember
     * @return 받은 요청 리스트
     * @Breif 플친 끊기 & 플친 신청 거절하기
     */
    @DeleteMapping("reject")
    public ResponseEntity<?> removeFriend(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody FriendDTO fromMember){

        List<FriendEntity> friendEntities = friendService.removeFriend(user.getMember().getMemberNo(), fromMember.getFromMemberNo());

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.12
     * @param user
     * @param toMember
     * @return 나의 플친 리스트
     * @Breif 플친 차단하기
     */
    @PutMapping("block")
    public ResponseEntity<?> blockFriend(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody FriendDTO toMember){

        List<FriendEntity> blockedFriends = friendService.blockFriend(user.getMember().getMemberNo(), toMember.getToMemberNo());

        List<FriendDTO> friendDTOS = blockedFriends.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }


}
