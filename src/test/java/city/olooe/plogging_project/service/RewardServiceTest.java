package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.RewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RewardServiceTest {
    @Autowired
    private RewardService rewardService;
    @Autowired
    private RewardRepository rewardRepository;

    @Transactional
    @Test
    @DisplayName("리워드 내역 전체 조회")
    public void GetRewardListTest() {
        log.info("전체 조회 : {}", rewardService.GetRewardList());
    }

    @Transactional
    @Test
    @DisplayName("리워드 유형 별 조회")
    public void GetRewardTypeList() {
        RewardEntity rewardEntity = RewardEntity.builder()
                .type(RewardTypeStatus.Challenge)
                .name("service test")
                .detail("detail service")
                .build();
        rewardRepository.save(rewardEntity);
        // log.info("유형 별 조회 : {}", rewardService.GetRewardTypeList(rewardEntity));
    }
}
