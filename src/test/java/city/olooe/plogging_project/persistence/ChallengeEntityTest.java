package city.olooe.plogging_project.persistence;

import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.Builder.Default;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ChallengeEntityTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("챌린지 생성 테스트")
    public void createChallenge() {
        MemberEntity entity;
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                .blind(true)
                .host(MemberEntity.builder().memberNo(1L).build())
                .title("챌린지 생성 테스트")
                .content("테스트 내용")
                .personnel(5L)
                .build();
        log.info("{}", challengeRepository.save(challengeEntity));
    }
}
