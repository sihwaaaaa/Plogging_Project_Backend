package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.UniqueConstraint;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    /**
     * @param type(유형)
     * @return List<PointHistoryEntity>
     * @Author 이재원
     * @Date 23.06.12     *
     * @Brief 포인트 유형별 조회
     */
    @Transactional
    public List<PointHistoryEntity> GetPointList(Long pointNo, String type) {
        return pointHistoryRepository.findByPointNoAndType(pointNo, RewardTypeStatus.valueOf(type));
    }

    /**
     * @param memberNo
     * @param type(유형)
     * @return List<PointHistoryEntity>
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 멤버 번호를 조회하여 포인트 유형 조회
     */
    @Transactional
    public List<PointHistoryEntity> GetMemberList(MemberEntity memberNo, String type) {
        return pointHistoryRepository.findByTypeAndMemberNo(RewardTypeStatus.valueOf(type), memberNo);
    }

    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 적립
     */
    @Transactional
    public PointHistoryEntity createPoint(final PointHistoryEntity entity) {
        return pointHistoryRepository.save(entity);
    }
    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 유형 조회
     */
    @Transactional
    public List<PointHistoryEntity> findByTypeList(String type) {
        return pointHistoryRepository.findByType(RewardTypeStatus.valueOf(type));
    }
    /*
     * @param MemberNo
     * @return currentPoint
     * @Author 이재원
     * @Date 23.06.14
     * @Brief 회원의 누적 포인트 조회
     */
    @Transactional
    public Long findByTotalPoint() {
        return pointHistoryRepository.totalPoint();
    }

    @Transactional
    public Long findBySumPoint(MemberEntity memberNo) {
        return pointHistoryRepository.sumPoint(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
    }

    @Transactional
    public List<PointHistoryEntity> test_memberNoList(MemberEntity memberNo) {
        return pointHistoryRepository.findByMemberNo(MemberEntity.builder().memberNo(1L).build());
    }
    @Transactional
    public List<PointHistoryEntity> GetfindByStatus(boolean status) {
        return pointHistoryRepository.findByStatus(status);
    }
}

//    public void test_pointHistoryList() {
//        List<PointHistoryEntity> historyEntityList = pointHistoryRepository.findByMemberNo(MemberEntity.builder().memberNo(1L).build());
//        log.info("{}", historyEntityList);
//    }