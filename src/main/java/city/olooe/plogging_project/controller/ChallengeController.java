package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


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

    // 전체조회
    @GetMapping("/challenge")
    public ResponseEntity<?> readChallenge(){
        List<ChallengeDTO> challengeDTOS = challengeService.serchAllCh();
        return ResponseEntity.ok().body(ResponseDTO.builder().data(challengeDTOS).build());

    }

    // 단일조회
    @GetMapping("/challenge/chDetail/{chNo}")
    public ChallengeDTO searchByChNo(@PathVariable Long chNo) {
        return challengeService.searchByChNo(chNo);
    }

    @PostMapping("/challenge")
    public ResponseEntity<?> createChallenge(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody ChallengeDTO challengeDTO){

        List<ChallengeEntity> challengeEntity = challengeService.createChallenge(challengeDTO,user.getMember().getMemberNo());
        log.info("{}", user.getMember().getMemberNo());
        List<ChallengeDTO> challengeDTOS = challengeEntity.stream().map(ChallengeDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(ResponseDTO.builder().data(challengeDTOS).build());
    }

}
