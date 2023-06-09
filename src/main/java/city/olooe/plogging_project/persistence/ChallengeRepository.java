package city.olooe.plogging_project.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeEntity;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
    ChallengeEntity findByChNo(Long chNo);

    // Optional<ChallengeEntity> findByChNoOptional(Long chNo);

    List<ChallengeEntity> findChallengeEntityByOrderByChNoDesc();
}
