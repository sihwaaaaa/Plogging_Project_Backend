package city.olooe.plogging_project.persistence;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
public class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    ChallengeScheduleRepository challengeScheduleRepository;

    @Autowired
    SchedulMemberRepository schedulMemberRepository;


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
                .startDate(new Date())
                .endDate(new Date())
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
        log.info("{}", challengeRepository.findByChNo(59L).getTitle());
    }

    /*
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
                .chNo(ChallengeEntity.builder().chNo(10L).build())
                .challenger(MemberEntity.builder().memberNo(12L).build())
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
        // 챌린지 일정정에 참여한 챌린지원 일정취소 , 일정삭제 , 가입한챌린지원 탈퇴, -> 챌린지 삭제
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
                .chNo(ChallengeEntity.builder().chNo(10L).build())
                .mapNo(MapEntity.builder().mapNo(2L).build())
                .startDate(new Date())
                .endDate(new Date())
                .build();
        log.info("{}", challengeScheduleRepository.save(challengeScheduleEntity));
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     * @brief: 챌린지 일정참여
     */
//    @Test
//    @DisplayName("챌린지원의 챌린지 일정참여")
//    public void scheduleaddMember(){
//        SchedulMemberEntity schedulMemberEntity = SchedulMemberEntity.builder()
//                .challenger(MemberEntity.builder().memberNo(12L).build())
//                .scheduleNo(ChallengeScheduleEntity.builder().scheduleNo(4L).build())
//                .chNo(ChallengeEntity.builder().chNo(10L).build())
//                .build();
//        schedulMemberRepository.save(schedulMemberEntity);
//    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     * @brief: 챌린지 일정취소
     */
    @Test
    @DisplayName("챌린지원이 챌린지 일정참여 취소")
    public void scheduleCancle(){
        // 챌린지 번호는 10번 , 맴버는 12번, 스케쥴일정번호는 4번이다
        // 10번챌린지의 4번의 일정에 참여한 12번 맴버를 취소시켜야한다

        // 10번 챌린지의 참여한 챌린지원을 가져오자
//        ChallengeMemberEntity challengeMemberEntity = challengeMemberRepository.findByChNoAndChallenger(
//                ChallengeEntity.builder().chNo(10L).build(), MemberEntity.builder().memberNo(12L).build()
//        );
        // 챌린지 일정에서 위의 조회해온 챌린지원을 확인하자
        SchedulMemberEntity schedulMemberEntity = schedulMemberRepository.findByChNoAndScheduleNoAndChallenger(
                ChallengeEntity.builder().chNo(10L).build(),
                ChallengeScheduleEntity.builder().scheduleNo(4L).build(),
                MemberEntity.builder().memberNo(12L).build()
        );

        // 삭제
        schedulMemberRepository.delete(schedulMemberEntity);
    }


    /**
     * @author : 김민수
     * @date: '23.06.05
     * @brief: 챌린지 일정삭제
     */
//    @Test
//    @DisplayName("챌린지 일정삭제")
////    @Transactional
//    public void deleteSchedule() {
//        // 일정을 삭제하기전 일정에 참여한 챌린지원들의 일정취소처리
//        SchedulMemberEntity schedulMemberEntity = schedulMemberRepository.findBySmno(4L);
//        if (schedulMemberEntity != null) {
//            SchedulMemberEntity cancle = schedulMemberRepository.findByChNoAndScheduleNoAndChallenger(
//                    ChallengeEntity.builder().chNo(10L).build(),
//                    ChallengeScheduleEntity.builder().scheduleNo(4L).build(),
//                    MemberEntity.builder().memberNo(12L).build()
//            );
//            // 삭제
//            schedulMemberRepository.delete(cancle);
//        }
//        ChallengeScheduleEntity challengeScheduleEntity = challengeScheduleRepository.findByScheduleNo(4L);
//        challengeScheduleRepository.delete(challengeScheduleEntity);
//        log.info("{}", "챌린지 일정이 삭제되었습니다");
//    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     * @brief: 챌린지 맴버삭제
     */
//    @Test
//    @DisplayName("챌린지 맴버삭제")
//    @Transactional
//    public void cmemberDelete(){
//        ChallengeMemberEntity challengeMemberEntity =
//                challengeMemberRepository
//                        .findByChNoAndChallenger
//                                (ChallengeEntity.builder().chNo(10L).build(),
//                                        MemberEntity.builder().memberNo(12L).build());
//        challengeMemberRepository.delete(challengeMemberEntity);
//    }
}
