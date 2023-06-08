package city.olooe.plogging_project.persistence;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.DonationEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.ProductEntity;
import city.olooe.plogging_project.model.RewardEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
/**
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: RewardRepository CRUD 테스트
 */
public class RewardRepositoryTest {
    @Autowired
    RewardRepository rewardRepository;

    @Test
    @DisplayName("리워드 적립 테스트")
    public void createReward() {
        // RewardEntity reward = RewardEntity.builder()
        // .tradePoint(50d)
        // .memberEntity(MemberEntity.builder().memberNo(1L).build())
        // .donationEntity(DonationEntity.builder().dno(1L).build())
        // .build();
        RewardEntity reward = RewardEntity.builder()
                .tradePoint(50L)
                .memberEntity(MemberEntity.builder().memberNo(1L).build())
                // .donationEntity(DonationEntity.builder().dno(1L).build())
                .productEntity(ProductEntity.builder().pno(1L).build())
                .build();

        log.info("{}", rewardRepository.save(reward));
    }

    @Test
    @DisplayName("리워드 전체 조회 테스트")
    public void testFindAllReward() {
        List<RewardEntity> rewardEntities = rewardRepository.findAll();
        log.info("리워드 전체 조회 테스트 : {}" + rewardEntities);
        System.out.println("sysout으로 한 리워드 전체 조회 테스트 : {}" + rewardEntities);
    }

    @Test
    @DisplayName("리워드 조건 조회 테스트")
    public void testFindByIdReward() {
        log.info("리워드 조회 테스트 : {}", rewardRepository.findById(3L));
    }

    @Test
    @DisplayName("리워드 수정 테스트")
    public void testUpdateReward() {
        RewardEntity rewardupdate = RewardEntity.builder().rewardNo(3L)
                .tradePoint(10L)
                .type("랜덤박스 수정")
                .rewardDate(new Date())
                .build();

        log.info("{}", rewardRepository.save(rewardupdate));
    }
}