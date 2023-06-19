package city.olooe.plogging_project.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import city.olooe.plogging_project.model.ChallengeMemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.model.MemberEntity;
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




}
