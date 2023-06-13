package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.service.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ChallengeControllerTest {
    @Autowired
    private ChallengeService challengeService;

    @Test
    public void createChallenge() throws Exception {
        ChallengeDTO challengeDTO = new ChallengeDTO();
    }
}
