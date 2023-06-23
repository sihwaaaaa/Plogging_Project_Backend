package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.*;
import city.olooe.plogging_project.dto.friend.FriendDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.ChallengeScheduleEntity;
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

import static java.util.stream.Collectors.toList;


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

    /**
     * @author : 김민수
     * @date: '23.06.08
     *
     * @param: -
     * @return: ResponseEntity
     *
     * @brief: 챌린지 전체 조회
     */
    // 전체조회
    @GetMapping("/challenge")
    public ResponseEntity<?> readChallenge(){
        List<ChallengeDTO> challengeDTOS = challengeService.serchAllCh();
        return ResponseEntity.ok().body(ResponseDTO.builder().data(challengeDTOS).build());

    }


    /**
     * @author : 김민수
     * @date: '23.06.19
     *
     * @param: -
     * @return: ResponseEntity
     *
     * @brief: 챌린지 맴버 전체조회
     */
    @GetMapping("challengeMember")
    public ResponseEntity<?> chMemberList(){
        List<ChallengeMemberDTO> chMemberListDto = challengeService.chMemberList();
        return ResponseEntity.ok().body(ResponseDTO.builder().data(chMemberListDto).build());
    }

    /**
     * @author : 김민수
     * @date: '23.06.19
     *
     * @param: -
     * @return: ResponseEntity
     *
     * @brief: 해당된 챌린지 맴버들만 전체조회
     */
//    @GetMapping("challengeMemberList/{chNo}")
//    public ResponseEntity<?> chMemberList(@PathVariable ChallengeMemberDTO chNo){
//        List<ChallengeMemberEntity> challengeMemberEntities = challengeService.chMembers(chNo);
//        return ResponseEntity.ok().body(ResponseDTO.builder().data(challengeMemberEntities).build());
//    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: chNo
     * @return: ChallengeDTO
     *
     * @brief: 챌린지 개별 조회
     */
    // 단일조회
    @GetMapping("/challenge/chDetail/{chNo}")
    public ChallengeDTO searchByChNo(@PathVariable Long chNo) {
        return challengeService.searchByChNo(chNo);
    }


    /**
     * @author : 김민수
     * @date: '23.06.10
     *
     * @param: challengeDTO
     * @return: ResponseEntity
     *
     * @brief: 챌린지 생성
     */
    @PostMapping("/challenge")
    public ResponseEntity<?> createChallenge(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody ChallengeDTO challengeDTO){

        List<ChallengeEntity> challengeEntity = challengeService.createChallenge(challengeDTO,user.getMember().getMemberNo()) ;
        log.info("{}", user.getMember().getMemberNo());
        log.info("{}", challengeDTO);
        List<ChallengeDTO> challengeDTOS = challengeEntity.stream().map(ChallengeDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(ResponseDTO.builder().data(challengeDTOS).build());
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: challengeMemberDTO , chNo, user
     * @return: -ChallengeMemberEntity
     *
     * @brief: 챌린지 가입
     */
    @PostMapping("/challenge/chDetail/{chNo}")
    public ChallengeMemberEntity challengeJoin(ChallengeMemberDTO challengeMemberDTO,@PathVariable Long chNo, @AuthenticationPrincipal ApplicationUserPrincipal user){
        ChallengeMemberEntity challengeMemberEntity = challengeService.challengeJoin(challengeMemberDTO, chNo, user.getMember().getMemberNo());
        log.info("{}", challengeMemberEntity);
        return challengeMemberEntity;
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: bno
     * @return: -
     *
     * @brief: 챌린지 일정생성
     */
    @PostMapping("/ploggingCreate")
    public ChallengeScheduleEntity ploggingCreate(@RequestBody ChallengeScheduleDTO challengeScheduleDTO){
//        log.info("chNo : {}", chNo);
        log.info("challengeScheduleDTO : {}", challengeScheduleDTO);
        ChallengeScheduleEntity challengeScheduleEntity = challengeService.scheduleCreate(challengeScheduleDTO);
        log.info("{}", challengeScheduleEntity);
        return challengeScheduleEntity;
    }

    @GetMapping("/ploggingList/{chNo}")
    public ResponseEntity<?> getChallengeSchedules(@PathVariable Long chNo) {
        log.info("chNo: {}", chNo);
        ChallengeEntity challengeEntity = new ChallengeEntity(chNo);
        List<ChallengeScheduleEntity> schedules = challengeService.readChSchedule(challengeEntity);

        List<ChallengeScheduleDTO> friendDTOS = schedules.stream()
                .map(ChallengeScheduleDTO::new)
                .collect(toList());
        log.info("Challenge schedules: {}", schedules);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(friendDTOS).build());
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: bno
     * @return: -
     *
     * @brief: 챌린지 삭제
     */
    @DeleteMapping("/challenge/chDetail/{chNo}")
    public void delete(@PathVariable Long bno) {
        challengeService.delete(bno);
    }

}
