package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.dto.ChallengeScheduleDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeScheduleEntity;

import java.util.List;

@Repository
public interface ChallengeScheduleRepository extends JpaRepository<ChallengeScheduleEntity, Long> {
    List<ChallengeScheduleEntity> findByChNo(ChallengeEntity chNo);
}
