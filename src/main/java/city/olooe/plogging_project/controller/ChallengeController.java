package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


/**
 * @author : 김민수
 *
 * @date : 2023.06.11
 *
 * @brief : 챌린지 controller
 */
@RequiredArgsConstructor
@RestController
@Slf4j
//@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private final ChallengeService challengeService;

    @GetMapping("/challenge")
    public ResponseEntity<?> readChallenge(){
        List<ChallengeDTO> challengeDTOS = challengeService.serchAllCh();
        return ResponseEntity.ok().body(ResponseDTO.builder().data(challengeDTOS).build());

    }

    @PostMapping("/challenge")
    public ResponseEntity<?> createChallenge(@RequestBody ChallengeDTO challengeDTO){

        ChallengeEntity challengeEntity = challengeService.createChallenge(challengeDTO);

        return ResponseEntity.ok(challengeEntity);
    }

}
