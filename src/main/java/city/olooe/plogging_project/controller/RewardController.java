package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.service.RewardService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reward")
@RequiredArgsConstructor
public class RewardController {
    @AutoConfigureOrder
    private final RewardService rewardService;

    /*
     * @Author 이재원
     * @Date 23.06.13
     * @param rewardNo
     * @param type
     * @return List<RewardEntity>
     * @Brief 리워드 전체 조회
     */
    @GetMapping("/list")
    public List<RewardEntity> myRewardList() {
        return rewardService.GetRewardList();
    }

    @GetMapping("/list/{type}")
    public List<RewardEntity> GetRewardType(@PathVariable String type) {
        return rewardService.GetRewardTypeList(type);
    }
}
