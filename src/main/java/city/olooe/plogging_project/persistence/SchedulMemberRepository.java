package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.dto.ChallengeScheduleDTO;
import city.olooe.plogging_project.dto.ScheduleMemberDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeScheduleEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.SchedulMemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface SchedulMemberRepository extends JpaRepository<SchedulMemberEntity,Long> {

    List<SchedulMemberEntity> findByChNoAndScheduleNo (ChallengeEntity chNo, ChallengeScheduleEntity scheduleNo);

    SchedulMemberEntity deleteBySmno (Long smno);
    SchedulMemberEntity findBySmno(Long smno);
//    SchedulMemberEntity findBySmno(ScheduleMemberDTO smno);
    SchedulMemberEntity findByChNoAndScheduleNoAndChallenger (ChallengeEntity chNo, ChallengeScheduleEntity scheduleNo, MemberEntity memberNo);
//    SchedulMemberEntity findByChNoAndScheduleNoAndChallenger (ChallengeScheduleDTO challengeScheduleDTO);

//    @Query("select sch from SchedulMemberEntity sch left join sch.schMembersCnt cm where cm.scheduleNo = :scheduleNo and cm.chNo =:chNo order by cm.scheduleNo")
//    List<SchedulMemberEntity> findMyScheduleMembers(@Param("scheduleNo")  Long scheduleNo,@Param("chNo") ChallengeEntity chNo);
    List<SchedulMemberEntity> findByScheduleNo (ChallengeScheduleEntity scheduleNo);
}
