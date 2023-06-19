package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.RewardTypeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import city.olooe.plogging_project.model.RewardEntity;

import javax.transaction.Transactional;
import java.util.List;


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
    @Transactional
    public void createReward() {
        RewardEntity rewardEntity = RewardEntity.builder()
                .detail("test")
                .name("test")
                .type(RewardTypeStatus.Product)
                .build();
        log.info("{}", rewardEntity);
    }

    @Test
    @DisplayName("리워드 유형 조회 테스트(랜덤박스 구성품)")
    @Transactional
    public void findByProductListTest() {
        List<RewardEntity> productlist = rewardRepository.findByType(RewardTypeStatus.Product);

        log.info("{}", productlist);
    }

    @Test
    @DisplayName("리워드 유형 조회 테스트(기부처)")
    public void findByDonationListTest() {
        List<RewardEntity> donationlist = rewardRepository.findAllByType(RewardTypeStatus.Donation);

        log.info("{}", donationlist);
    }
}