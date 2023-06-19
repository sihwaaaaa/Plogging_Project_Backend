package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.*;
import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 회원 리스트
     * @Brief 모든 회원 정보 확인
     */
    @GetMapping("member")
    public ResponseEntity<?> getMembers(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<MemberEntity> members = adminService.getMemberAll();

        List<MemberDTO> memberDTOS = members.stream()
                .map(member -> MemberDTO.builder()
                        .memberNo(member.getMemberNo())
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .regDate(member.getRegDate())
                        .birth(member.getBirth())
                        .gender(member.getGender())
                        .authProvider(member.getAuthProvider())
                        .authList(member.getAuthEntities().stream()
                                .map(auth -> auth.getAuthority().getKey())
                                .collect(toList()))
                        .build())
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(memberDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 리워드 업체 리스트
     * @Brief 모든 리워드 업체 정보 확인
     */
    @GetMapping("reward")
    public ResponseEntity<?> getRewards(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<RewardEntity> rewards = adminService.getRewardAll();

        List<RewardDTO> rewardDTOS = rewards.stream()
                .map(RewardDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(rewardDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 포인트히스토리 리스트
     * @Brief 모든 포인트 히스토리 정보 확인
     */
    @GetMapping("point")
    public ResponseEntity<?> getPoints(@AuthenticationPrincipal ApplicationUserPrincipal user){
        List<PointHistoryEntity> points = adminService.getPointHistoryAll();
        points.forEach(p -> log.warn("entity {}", p));

        List<PointHistoryDTO> pointHistoryDTOS = points.stream()
                .map(PointHistoryDTO::new)
                .collect(toList());
        pointHistoryDTOS.forEach(p -> log.warn("dto {}", p));

        return ResponseEntity.ok().body(ResponseDTO.builder().data(pointHistoryDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 플로깅 리스트
     * @Brief 모든 플로깅 히스토리 정보 확인
     */
    @GetMapping("plogging")
    public ResponseEntity<?> getPloggings(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<PloggingEntity> ploggings = adminService.getPloggingAll();

        List<PloggingDTO> ploggingDTOS = ploggings.stream()
                .map(PloggingDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(ploggingDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 챌린지 리스트
     * @Brief 모든 챌린지 정보 확인
     */
    @GetMapping("challenge")
    public ResponseEntity<?> getChallenges(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<ChallengeEntity> challenges = adminService.getChallengeAll();

        List<ChallengeDTO> challengeDTOS = challenges.stream()
                .map(ChallengeDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(challengeDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @param user
     * @return 게시글 리스트
     * @Brief 모든 게시글 정보 확인
     */
    @GetMapping("board")
    public ResponseEntity<?> getBoards(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<BoardEntity> boards = adminService.getBoardAll();

        List<BoardDTO> boardDTOS = boards.stream()
                .map(BoardDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(boardDTOS).build());
    }

    /**
     * @Author 천은경
     * @Date 23.06.15
     * @param user
     * @return 추천경로 리스트
     * @Brief 모든 추천경로 정보 확인
     */
    @GetMapping("map")
    public ResponseEntity<?> getMaps(@AuthenticationPrincipal ApplicationUserPrincipal user){
        List<MapEntity> maps = adminService.getMapAll();

        List<MapDTO> mapDTOS = maps.stream()
                .map(MapDTO::new)
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(mapDTOS).build());
    }
}
