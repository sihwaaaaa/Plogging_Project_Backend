package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.FriendDTO;
import city.olooe.plogging_project.model.FriendEntity;
import city.olooe.plogging_project.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
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
    @ResponseBody
    @GetMapping("/friendList/{memberNo}/{status}")
    public List<FriendDTO> myFriendList(@PathVariable Long memberNo, @PathVariable String status) {

        List<FriendEntity> friendEntities = friendService.GetMyFriendList(memberNo, status);

        return friendEntities.stream()
                .map(FriendDTO::new)
                .collect(toList());
    }
}
