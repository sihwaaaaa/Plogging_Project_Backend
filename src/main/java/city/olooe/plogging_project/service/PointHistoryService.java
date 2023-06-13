package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
     * @Brief 멤버 번호를 조회하여 포인트 유형 조회     *
     */
    @Transactional
    public List<PointHistoryEntity> GetMemberList(MemberEntity memberNo ,String type) {
        return pointHistoryRepository.findByTypeAndMemberNo(RewardTypeStatus.valueOf(type), memberNo);
    }
    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 생성
     *
     */
    @Transactional
    public PointHistoryEntity createPoint(PointHistoryDTO historyDTO) {
        return pointHistoryRepository.save(historyDTO.changeEntity());
    }
    @Transactional
    public List<PointHistoryEntity> findByTypeList(String type) {
        return pointHistoryRepository.findByType(RewardTypeStatus.valueOf(type));
    }
}
