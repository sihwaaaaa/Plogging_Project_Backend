package city.olooe.plogging_project.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import city.olooe.plogging_project.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.map.MapEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ChallengeEntityTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    ChallengeScheduleRepository challengeScheduleRepository;

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 생성 테스트
     */
    @Test
    @DisplayName("챌린지 생성 테스트")
    public void createChallenge() {
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                .blind(true)
                .host(MemberEntity.builder().memberNo(12L).build())
                .title("챌린지 생성 테스트")
                .content("테스트 내용")
                .personnel(5L)
                .status(ChallengeStatus.CHALLENGEBEFORE)
                .build();
        log.info("{}", challengeRepository.save(challengeEntity));
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 목록 전체조회
     */
    @Test
    @DisplayName("챌린지 목록 전체조회")
    @Transactional
    public void readAllChallenge() {
        List<ChallengeEntity> challengeList = challengeRepository.findAll();
        log.info("챌린지 전체 조회 테스트 : {}" + challengeList);
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 단일 조회
     */
    @Test
    @DisplayName("챌린지 단일 조회")
    public void readChallenge() {
        log.info("{}", challengeRepository.findByChNo(5L));
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 삭제 테스트
     */
    @Test
    @DisplayName("챌린지 삭제 테스트")
    public void deleteChallenge() {
        ChallengeEntity challenge = challengeRepository.findByChNo(8L);
        challengeRepository.delete(challenge);
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 가입 테스트
     */
    @Test
    @DisplayName("챌린지 가입 테스트")
    public void updateChallenge() {
        ChallengeMemberEntity challengeMemberEntity = ChallengeMemberEntity.builder()
                .chNo(ChallengeEntity.builder().chNo(5L).build())
                .challenger(MemberEntity.builder().memberNo(6L).build())
                .build();
        log.info("{}", challengeMemberRepository.save(challengeMemberEntity));
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 탈퇴 테스트
     */
    @Test
    @DisplayName("챌린지 탈퇴 테스트")
    public void deleteChallengeMember() {
        ChallengeMemberEntity challengeMemberEntity = challengeMemberRepository.findByCmemberNo(2L);
        challengeMemberRepository.delete(challengeMemberEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 일정추가
     */
    @Test
    @DisplayName("챌린지 일정추가")
    public void createSchedule() {
        ChallengeScheduleEntity challengeScheduleEntity = ChallengeScheduleEntity.builder()
                .chNo(ChallengeEntity.builder().chNo(5L).build())
                .mapNo(MapEntity.builder().mapNo(1L).build())
                .build();
        log.info("{}", challengeScheduleRepository.save(challengeScheduleEntity));
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 일정삭제
     */
    @Test
    @DisplayName("챌린지 일정삭제")
    public void deleteSchedule() {
        ChallengeScheduleEntity challengeScheduleEntity = challengeScheduleRepository.findByScheduleNo(2L);
        challengeScheduleRepository.delete(challengeScheduleEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지별 인원 카운트
     */
    @Test
    @DisplayName("해당 회원이 가입한 챌린지 목록")
    public void challengeMemberCount(){
//        MemberEntity member = memberRepository.findByUserId("pkkj");
//        log.info("{}", member);
    }
}
