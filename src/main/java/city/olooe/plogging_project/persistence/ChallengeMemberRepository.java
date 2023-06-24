package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.dto.ChallengeMemberDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeMemberEntity;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface ChallengeMemberRepository extends JpaRepository<ChallengeMemberEntity, Long> {
    ChallengeMemberEntity findByCmemberNo(Long cmemberNo);

//    ChallengeMemberEntity findByChNo(Long chNo);

    List<ChallengeMemberEntity> findByChNo(ChallengeMemberEntity chNo);

    List<ChallengeMemberEntity> findChallengeMemberEntityByOrderByChNoDesc();

    ChallengeMemberEntity findByChNoAndChallenger(ChallengeMemberDTO chNo, ChallengeMemberDTO challenger);
}
