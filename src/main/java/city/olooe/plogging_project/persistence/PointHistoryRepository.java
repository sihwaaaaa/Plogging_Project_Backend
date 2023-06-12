package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.RewardTypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.PointHistoryEntity;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {
//    PointHistoryEntity findByTypeAndPoint(RewardTypeStatus type, Long point);
}
