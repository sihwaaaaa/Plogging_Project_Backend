package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.aliasing.qual.Unique;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@SpringBootTest
@Slf4j
public class PointHistoryTest {

    @Autowired
    private PointHistoryService pointHistoryService;

    private PointHistoryRepository pointHistoryRepository;

    /**
     * @Author 이재원
     * @Date 23.06.12
     * @param
     * @param
     * @return List<PointHistoryEntity>
     * @Brief 포인트 유형별 조회
     */
    @Test
    @DisplayName("포인트 누적 테스트")
//    @Transactional
    public void testCreatePointHistory() {
        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
                .memberNo(75L)
                .type(RewardTypeStatus.Product.getKey())
                .point(RewardTypeStatus.Product.getValue())
                .rewardNo(1L)
                .build();

        PointHistoryEntity historyEntity = PointHistoryDTO.toEntity(historyDTO);
        log.info("{}", pointHistoryRepository.save(historyEntity));
    }
    @Test
    public void updatePointTest(PointHistoryDTO historyDTO, Long point) {
//        PointHistoryEntity memberNo = pointHistoryRepository.findByMemberNo(historyDTO.getMemberNo());
//        Long tradePoint = historyDTO.changePoint(point);

//        PointHistoryEntity historyEntity = new PointHistoryEntity(memberNo, point);
//        pointHistoryRepository.save(historyEntity);
//        log.info("{}", historyEntity);
    }

    @Test
    @DisplayName("포인트 유형 별 조회 테스트")
    public void testPointType() {
        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
                .pointNo(5L)
                .type(RewardTypeStatus.Donation.getKey())
                .build();

        List<PointHistoryEntity> historyEntities = pointHistoryService.GetPointList(historyDTO.getPointNo(),
                historyDTO.getType());

        log.info("조회 test : {}", historyEntities);
    }

//    @Test
//    @DisplayName("포인트 멤버 별 조회 테스트")
//    public void testMemberNoType() {
//        // RewardTypeStatus typeStatus = RewardEntity.builder().build().getType();
//        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
////                .memberNo(MemberEntity.builder().memberNo(1L).build())
//                .type(RewardTypeStatus.Product.getKey())
//                .build();
////        List<PointHistoryEntity> historyEntities = pointHistoryService.GetMemberList(historyDTO.getMemberNo(),
//                historyDTO.getType());
//        log.info("멤버 별 조회 Test : {}", historyEntities);
//    }

    @Test
    @DisplayName("포인트 유형 별 조회 테스트")
    public void findByTypeTest() {
        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
                .type(RewardTypeStatus.Product.getKey())
                .build();
        List<PointHistoryEntity> historyEntities = pointHistoryService.findByTypeList(historyDTO.getType());
        log.info("Point Type : {}", historyEntities);
    }

//    @Test
//    @DisplayName("누적 포인트 조회")
//    public void totalPointTest() {
//        Long totalPoint = pointHistoryService.findByTotalPoint();
//        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
//                .memberNo(MemberEntity.builder().memberNo(1L).build())
//                .point(totalPoint)
//                .type(RewardTypeStatus.Product.getKey())
//                .build();
//        log.info("total point Test : {}", historyDTO);
//    }

    @Test
    @DisplayName("멤버 번호를 통해 현재 포인트 조회")
    public void sumPointTest() {
//        PointHistoryDTO historyDTO = PointHistoryDTO.builder()
//                .memberNo(MemberEntity.builder().memberNo(1L).build())
//                .point(pointHistoryRepository.sumPoint(memberNo))
//                .build();
//        pointHistoryRepository.findById(memberNo.getMemberNo());
//        log.info("현재 포인트 조회 : {}", pointHistoryRepository.sumPoint(historyDTO.getMemberNo()));
    }
}
