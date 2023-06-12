package city.olooe.plogging_project.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import city.olooe.plogging_project.model.RewardEntity;


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
        RewardEntity rewardEntity = RewardEntity.builder()
                .detail("test")
                .name("test")

                .build();
    }

}