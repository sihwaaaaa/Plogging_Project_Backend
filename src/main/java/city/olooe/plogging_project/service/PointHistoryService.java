package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.stereotype.Service;

import javax.persistence.UniqueConstraint;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;
    /**
     * @Author 이재원
     * @Date 23.06.12     *
     * @param type(유형)
     * @return List<PointHistoryEntity>
     * @Brief 포인트 유형별 조회
     */
    @Transactional
    public List<PointHistoryEntity> GetPointList(Long pointNo ,String type) {
        return pointHistoryRepository.findByPointNoAndType(pointNo, RewardTypeStatus.valueOf(type));
    }
    /**
     * @Author 이재원
     * @Date 23.06.12
     * @param memberNo
     * @param type(유형)
     * @return List<PointHistoryEntity>
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
    public List<PointHistoryEntity> updatePoint(PointHistoryDTO historyDTO) {

        PointHistoryEntity memberNo = pointHistoryRepository.findByMemberNo(historyDTO.getMemberNo());
        Long tradePoint = historyDTO.getPoint();

        PointHistoryEntity historyEntity = new PointHistoryEntity();
        pointHistoryRepository.save(historyEntity);

        return pointHistoryRepository.findByMemberNoAndPoint(historyDTO.getMemberNo(), historyDTO.getPoint());
    }


    @Transactional
    public PointHistoryEntity createPoint(PointHistoryDTO historyDTO) {
        return pointHistoryRepository.save(historyDTO.changeEntity());
    }
        // 기존 포인트 값에 새로운 값을 더하여 연산합니다.
//        historyDTO.changePoint(historyDTO.changePoint(historyDTO.getPoint()));
//        Long updatedPoint = historyDTO.getPoint();


        // 연산된 값을 저장하거나 업데이트합니다.
//        pointHistoryRepository.save(pointHistoryEntity);
//        return pointHistoryRepository.save(historyDTO.changePoint(historyDTO.getPoint()));

    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 유형 조회
     *
     */
    @Transactional
    public List<PointHistoryEntity> findByTypeList(String type) {
        return pointHistoryRepository.findByType(RewardTypeStatus.valueOf(type));
    }

    /*
     * @param
     * 
     * @return type
     * 
     * @Author 이재원
     * 
     * @Date 23.06.13
     * 
     * @Brief 포인트 누적 메서드
     * 
     */
//     @Transactional
//     public PointHistoryDTO TradePointList(PointHistoryDTO historyDTO) {
//         historyDTO.TradePoint(historyDTO.getPoint());
//
//
//         return pointHistoryRepository.save(historyDTO.setPoint(historyDTO.getPoint())
//     }
}