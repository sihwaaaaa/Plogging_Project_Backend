package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeScheduleEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.SchedulMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Repository
public interface SchedulMemberRepository extends JpaRepository<SchedulMemberEntity,Long> {

    SchedulMemberEntity findBySmno(Long smno);
    SchedulMemberEntity findByChNoAndScheduleNoAndChallenger (ChallengeEntity chNo, ChallengeScheduleEntity scheduleNo, MemberEntity memberNo);
}
