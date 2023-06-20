package city.olooe.plogging_project.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import city.olooe.plogging_project.dto.*;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
public class ChallengeServiceTest {

    @Autowired
    ChallengeService challengeService;

    /**
     * @author : 김민수
     * @date: '23.06.09
     * 
     * @param: -
     * @return: -
     * 
     * @brief: 챌린지 생성 테스트
     */
    @Test
    @Transactional
    @Rollback(false)
    public void createChallengeTest() {
        ChallengeDTO challengeDTO = ChallengeDTO.builder()
                .blind(true)
                .memberNo(12L)
                .title("챌린지 서비스 테스트")
                .content("챌린지 서비스 테스트 내용")
                .regDate(LocalDate.now())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .personnel(5L)
                .build();
//        ChallengeEntity challengeEntity = challengeService.createChallenge(challengeDTO);
//        log.info("{}",challengeEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.09
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 단일조회 테스트
     */
    @Test
    @Transactional
    public void searchByChNoTest(){
        // 테스트 데이터 설정
        Long chNo = 12L;
       ChallengeDTO challengeDTO = challengeService.searchByChNo(chNo);
        log.info("{}", challengeDTO);
    }

    /**
     * @author : 김민수
     * @date: '23.06.09
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 전체조회 테스트
     */
    @Test
    @Transactional
    public void searchByCh(){
        List<ChallengeDTO> challengeDTOS = challengeService.serchAllCh();
        log.info("{}", challengeDTOS);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 삭제 테스트
     */
    @Test
    @Transactional
    public void deleteTest(){
        // 테스트 데이터 설정
        Long chNo = 10L;
        ChallengeMemberEntity challengeMemberEntity = new ChallengeMemberEntity();
        challengeService.delete(chNo);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 가입 테스트
     */
//    @Test
//    @Transactional
//    public void challengeJoin(){
//        ChallengeMemberDTO challengeMemberDTO = ChallengeMemberDTO.builder()
//                .chNo(10L)
//                .challenger(62L)
//                .regDate(new Date())
//                .build();
//        challengeService.challengeJoin(challengeMemberDTO);
//    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 일정생성 테스트
     */
    @Test
//    @Transactional
    public void chScheduleCreate(){
        ChallengeScheduleDTO challengeScheduleDTO = ChallengeScheduleDTO.builder()
                .chNo(12L)
                .regDate(LocalDate.now())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .mapNo(1L)
                .build();
        challengeService.scheduleCreate(challengeScheduleDTO);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 일정참여
     */
    @Test
    public void scheduleJoin(){
        ScheduleMemberDTO scheduleMemberDTO = ScheduleMemberDTO.builder()
                .scheduleNo(11L)
                .chNo(29L)
                .challenger(62L)
                .build();
        challengeService.scheduleJoin(scheduleMemberDTO);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 맴버삭제
     */
    @Test
    public void chMemberDelete(){
        // 챌린지 No, 맴버 No 필요
        ChallengeMemberDTO challengeMemberDTO = ChallengeMemberDTO.builder()
                        .cmemberNo(10L)
                .chNo(12L).challenger(62L)
                                .build();
        challengeService.cmemberDelete(challengeMemberDTO);
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 일정참여 취소
     */
    @Test
//    @Transactional
    public void scheduleCancle(){
        // 챌린지 일정참여 취소 - 챌린지No, 챌린지 일정No, 맴버No 필요
        ScheduleMemberDTO scheduleMemberDTO = ScheduleMemberDTO.builder()
                .smno(8L)
                .scheduleNo(11L)
                .chNo(10L)
                .challenger(1L)
                .build();
        challengeService.scheduleCancle(scheduleMemberDTO);
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: -
     * @return: -
     *
     * @brief: 챌린지 일정삭제
     *
     */
//    @Test
//    @Transactional
//    public void scheduleDelete(){
//        // 테스트용 smno 데이터
//        Long smno = 10L;
//        ChallengeScheduleDTO challengeScheduleDTO = ChallengeScheduleDTO.builder()
//                .scheduleNo(11L)
//                .chNo(12L)
//                .mapNo(1L)
//                .build();
//        challengeService.scheduleDelete(smno,challengeScheduleDTO);
//
//    }








}
