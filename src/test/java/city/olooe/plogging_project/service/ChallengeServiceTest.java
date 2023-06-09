package city.olooe.plogging_project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.ChallengeMemberRepository;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.ChallengeScheduleRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ChallengeServiceTest {

    @Autowired
    ChallengeService challengeService;

    /**
     * @author : 김민수
     * @date: '23.06.08
     * 
     * @param: -
     * @return: ChallengeEntity
     * 
     * @brief: 챌린지 생성 테스트
     */
    @Test
    @Transactional
    public ChallengeEntity createChallengeTest() {
        ChallengeDTO challengeDTO = ChallengeDTO.builder()
                .blind(true)
                .host(MemberEntity.builder().memberNo(12L).build())
                .title("챌린지 서비스 테스트")
                .content("챌린지 서비스 테스트 내용")
                .personnel(5L)
                .status(ChallengeStatus.CHALLENGEBEFORE)
                .build();
        ChallengeEntity challengeEntity = challengeService.createChallenge(challengeDTO);
        return challengeEntity;
    }
}
