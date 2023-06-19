package city.olooe.plogging_project.persistence;

import java.util.List;

import city.olooe.plogging_project.model.MemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeEntity;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
    ChallengeEntity findByChNo(Long chNo);

    // Optional<ChallengeEntity> findByChNoOptional(Long chNo);

    List<ChallengeEntity> findChallengeEntityByOrderByChNoDesc();

    @Query("select ch from ChallengeEntity ch left join ch.ChallengeMembers cm where cm.challenger = :member order by cm.cmemberNo")
    List<ChallengeEntity> findMyChallenges(MemberEntity member, Pageable pageable);
}
