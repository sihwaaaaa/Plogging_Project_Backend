package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.FriendDTO;
import city.olooe.plogging_project.model.FriendEntity;
import city.olooe.plogging_project.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param memberNo
     * @param status
     * @return List<FriendDTO> 나의 팔로잉 상태별 플친 리스트
     * @Brief 팔로잉 상태별 플친 리스트 api
     */
    @GetMapping("/fromMe/{memberNo}/{status}")
    public List<FriendDTO> myFriendList(@PathVariable Long memberNo, @PathVariable String status) {

        List<FriendEntity> friendEntities = friendService.GetMyFriendList(memberNo, status);

        return friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
    }


    // 팔로워 상태별 플친 리스트
    @GetMapping("/toMe/{memberNo}/{status}")
    public List<FriendDTO> friendListToMe(@PathVariable Long memberNo, @PathVariable String status) {

        List<FriendEntity> friendEntities = friendService.getFriendListToMe(memberNo, status);

        return friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
    }

    // 플친 단일 조회


    // 플친 신청하기
//    @PostMapping
//    public FriendDTO requestFriend(Long fromMemberNo, Long toMemberNo) {
//        FriendEntity friendEntity = friendService.requestFriend(fromMemberNo, toMemberNo);
//
//    }

    // 플친 신청 취소하기

    // 플친 수락하기

    // 플친 끊기 & 플친 신청 거절하기

    // 플친 차단하기

    // 플친 차단 취소하기

}
