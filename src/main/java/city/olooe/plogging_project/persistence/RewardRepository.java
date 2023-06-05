package city.olooe.plogging_project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.RewardEntity;

/*
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: 기본 CURD 구현을 위한 RewardEntity jpa 구현체
 */
@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Long> {
    // List<RewardEntity> findByRewardNo(Long rewardNo);

    // List<RewardEntity> findAll();
}
