package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.PointHistoryEntity;

import java.util.List;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {
    /**
     * @author 이재원
     * @date 23.06.12
     * @param pointNo
     * @return List<PointHistoryEntity>
     * @Brief 포인트 내역 중 상태별 조회를 위한 메서드
     */
    List<PointHistoryEntity> findByPointNoAndType(Long pointNo, RewardTypeStatus type);

    /**
     * @author 이재원
     * @date 23.06.12
     * @param memberNo
     * @return List<PointHistoryEntity>
     * @Brief 멤버 번호를 조회하여 상태별 조회 메서드
     */
    List<PointHistoryEntity> findByTypeAndMemberNo(RewardTypeStatus type , MemberEntity memberNo);

    /**
     * @author 이재원
     * @date 23.06.12
     * @param
     * @return List<PointHistoryEntity>
     * @Brief 멤버 번호를 조회하여 상태별 조회 메서드
     */
    List<PointHistoryEntity> findByType(RewardTypeStatus type);

    /*
     * @author 이재원
     * @date 23.06.14
     * @param MemberEntity
     * @return MemberNo
     * @Brief 멤버 번호 조회
     */
    PointHistoryEntity findByMemberNo(MemberEntity memberNo);
    /*
     * @author 이재원
     * @date 23.06.14
     * @param MemberEntity
     * @return MemberNo
     * @Brief 멤버 번호의 포인트를 누적
     */
    List<PointHistoryEntity> findByMemberNoAndPoint(MemberEntity memberNo, Long point);
}
