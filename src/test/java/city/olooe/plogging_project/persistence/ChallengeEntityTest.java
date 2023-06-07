package city.olooe.plogging_project.persistence;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
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

    @Test
    @DisplayName("챌린지 생성 테스트")
    public void createChallenge() {
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                // .blind(true)
                .host(MemberEntity.builder().memberNo(12L).build())
                .title("챌린지 생성 테스트")
                .content("테스트 내용")
                .personnel(5L)
                .build();
        log.info("{}", challengeRepository.save(challengeEntity));
    }

    @Test
    @DisplayName("챌린지 목록 전체조회")
    public void readAllChallenge() {
        log.info("{}", challengeRepository.findAll());
    }

    @Test
    @DisplayName("챌린지 단일 조회")
    public void readChallenge() {
        challengeRepository.findByChNo(5L);
    }

    @Test
    @DisplayName("챌린지 삭제 테스트")
    public void deleteChallenge() {
        ChallengeEntity challenge = challengeRepository.findByChNo(4L);
        challengeRepository.delete(challenge);
    }

    @Test
    @DisplayName("챌린지 가입 테스트")
    public void updateChallenge() {
        ChallengeMemberEntity challengeMemberEntity = ChallengeMemberEntity.builder()
                .chNo(ChallengeEntity.builder().chNo(5L).build())
                .challenger(MemberEntity.builder().memberNo(6L).build())
                .build();
        log.info("{}", challengeMemberRepository.save(challengeMemberEntity));
    }

    @Test
    @DisplayName("챌린지 탈퇴 테스트")
    public void deleteChallengeMember() {
        challengeMemberRepository.findByCmemberNo(1L);
    }

}
