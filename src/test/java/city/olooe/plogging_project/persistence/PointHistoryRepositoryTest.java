package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Slf4j
public class PointHistoryRepositoryTest {

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Test
    @DisplayName("포인트 사용 내역 생성 테스트")
    @Transactional
    @Rollback(value = false)
    public void createPointHistoryTest() {
        PointHistoryEntity historyEntity = PointHistoryEntity.builder()
                .memberNo(MemberEntity.builder().memberNo(1L).build())
                .rewardNo(RewardEntity.builder().rewardNo(1L).build())
                .type(RewardTypeStatus.Donation)
                .point(RewardTypeStatus.Donation.getValue())
                .build();
        pointHistoryRepository.save(historyEntity);
        log.info("test{}", historyEntity);
    }

    @Test
    @DisplayName("포인트 사용 내역 전체 조회 테스트")
    public void findAllPointTest() {
        List<PointHistoryEntity> historyEntityList = pointHistoryRepository.findAll(Sort.by(Sort.Direction.DESC, "pointNo"));
        log.info("{}", historyEntityList);
    }

    @Test
    @DisplayName("리워드 유형 조회 테스트")
    public void findByTypeTest() {
        List<PointHistoryEntity> historyEntities = pointHistoryRepository.findByType(RewardTypeStatus.Donation);
        log.info("{}", historyEntities);
    }

    @Test
    public void findByIdTest() {
        log.info("{}", pointHistoryRepository.findById(1L));
    }

    @Test
    public void test_pointHistoryList() {
        List<PointHistoryEntity> historyEntityList = pointHistoryRepository.findByMemberNo(MemberEntity.builder().memberNo(1L).build());
        log.info("{}", historyEntityList);
    }

    @Test
    public void test_sumPoint() {
        log.info("{}", pointHistoryRepository.sumPoint(MemberEntity.builder().memberNo(1L).build()));
    }

    @Test
    @Transactional
    public void test_pointStatus() {
        List<PointHistoryEntity> historyEntities = pointHistoryRepository.findByStatus(false);
        log.info("Status Test : {}", historyEntities);
    }
}
