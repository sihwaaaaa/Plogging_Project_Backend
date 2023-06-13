package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.friend.FriendDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.FriendEntity;
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
     * @param userId
     * @param status
     * @return List<FriendDTO> 나의 팔로잉 상태별 플친 리스트
     * @Brief 팔로잉 상태별 플친 리스트 api
     */
    @GetMapping("/fromMe/{status}")
    public ResponseEntity<?> myFriendList(@AuthenticationPrincipal String userId, @PathVariable String status) {

        List<FriendEntity> friendEntities = friendService.GetMyFriendList(userId, status);

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.11
     * @param userId
     * @param status
     * @return 팔로워 상태별 플친 리스트
     * @Breif 팔로워 상태별 플친 리스트 api
     */
    @GetMapping("/toMe/{status}")
    public ResponseEntity<?> friendListToMe(@AuthenticationPrincipal String userId, @PathVariable String status) {
    //ResponseEntity<ResponseDTO<List<FriendDTO>>>

        List<FriendEntity> friendEntities = friendService.getFriendListToMe(userId, status);

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    // 플친 단일 조회
    // 플친 전체 조회
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
     * @param userId
     * @param friendDTO
     * @return 나의 플친 요청 리스트
     * @Brief 플친 신청하기
     */
    @PostMapping("request")
    public ResponseEntity<?> requestFriend(@AuthenticationPrincipal String userId, @RequestBody FriendDTO friendDTO) {

        List<FriendEntity> friendEntities = friendService.requestFriend(userId, friendDTO.getToMemberNo());

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

//    // 플친 신청 취소하기
//    @PutMapping("cancel")
//    public ResponseEntity<?> cancelRequest(@AuthenticationPrincipal String userId, @RequestBody FriendDTO toMember) {
//        List<FriendEntity> friendEntities = friendService.
//    }



    // 플친 수락하기
    @PutMapping("accept")
    public ResponseEntity<?> acceptFriend(@AuthenticationPrincipal String userId, @RequestBody FriendDTO fromMember){
        log.warn("플친 수락 데이터 확인 : {}", fromMember.getFromMemberNo());
        List<FriendEntity> friendEntities = friendService.acceptRequest(userId, fromMember.getFromMemberNo());
        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }



    // 플친 끊기 & 플친 신청 거절하기
    @DeleteMapping("reject")
    public ResponseEntity<?> removeFriend(@AuthenticationPrincipal String userId, @RequestBody FriendDTO fromMember){

        List<FriendEntity> friendEntities = friendService.removeFriend(userId, fromMember.getFromMemberNo());

        List<FriendDTO> friendDTOS = friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }



    // 플친 차단하기
    @PutMapping("block")
    public ResponseEntity<?> blockFriend(@AuthenticationPrincipal String userId, @RequestBody FriendDTO toMember){

        List<FriendEntity> blockedFriends = friendService.blockFriend(userId, toMember.getToMemberNo());

        List<FriendDTO> friendDTOS = blockedFriends.stream()
                .map(FriendDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    // 플친 차단 취소하기


}
