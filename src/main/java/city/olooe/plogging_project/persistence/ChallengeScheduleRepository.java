package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeScheduleEntity;

@Repository
public interface ChallengeScheduleRepository extends JpaRepository<ChallengeScheduleEntity, Long> {
    ChallengeScheduleEntity findByScheduleNo(Long scheduleNo);
}
