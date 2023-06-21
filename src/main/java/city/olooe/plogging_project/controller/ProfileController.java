package city.olooe.plogging_project.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ChallengeMemberDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.security.CustomUserDetails;
import city.olooe.plogging_project.service.ChallengeService;
import city.olooe.plogging_project.service.MemberService;
import city.olooe.plogging_project.service.PointHistoryService;
import city.olooe.plogging_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
@Slf4j
public class ProfileController {

  private final PointHistoryService pointService;

  // private final ChallengeService challengeService;
  private final MemberService memberService;

  private final ProfileService profileService;

  // private final ProfileService boardService;

  @GetMapping("challenge")
  public ResponseEntity<?> challenge(@AuthenticationPrincipal ApplicationUserPrincipal member) {

    // 해당 회원의 모든 챌린지들 조회 ( 페이져 = false )
    try {
      // List<ChallengeMemberEntity> challengeMemberEntities = profileService.search
      List<ChallengeEntity> challengeEntities = profileService.searchChallengeDetailByMember(member.getMember());

      // List<> challengeEntities = profileService.searchByMember(member.getMember());
      List<ChallengeDTO> challengeDTOs = challengeEntities.stream().map(ChallengeDTO::new)
          .collect(Collectors.toList());
      // System.out.println(challengeDTOs);
      ResponseDTO response = ResponseDTO.builder().data(challengeDTOs).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ResponseDTO response = ResponseDTO.builder().error("실패!").build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping("plogging")
  public ResponseEntity<?> plogging(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    try {
      List<PloggingEntity> ploggingEntities = profileService.searchPloggingByMember(member.getMember());

      log.info("{}", ploggingEntities);
      List<PloggingDTO> ploggingDTOs = ploggingEntities.stream().map(PloggingDTO::new).collect(Collectors.toList());
      ResponseDTO response = ResponseDTO.builder().data(ploggingDTOs).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ResponseDTO response = ResponseDTO.builder().error("실패!").build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping("board")
  public ResponseEntity<?> myBoard(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    try {
      List<BoardEntity> boardEntities = profileService.searchBoardByMember(member.getMember());
      log.info("{}", boardEntities);
      List<BoardDTO> boardDTOs = boardEntities.stream().map(BoardDTO::new).collect(Collectors.toList());
      ResponseDTO response = ResponseDTO.builder().data(boardDTOs).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ResponseDTO response = ResponseDTO.builder().error("실패!").build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping("point")
  public ResponseEntity<?> point(@AuthenticationPrincipal ApplicationUserPrincipal member) {

    try {
      List<PointHistoryEntity> pointList = pointService.GetMemberList(member.getMember(),
          RewardTypeStatus.Donation.getKey());
      List<PointHistoryDTO> pointDto = pointList.stream().map(PointHistoryDTO::new).collect(Collectors.toList());
      log.info("{}", pointList);
      // log.info("{}", o.get(0).getPointNo());
      // log.info("{}", pointDto.get(0).getPointNo());
      ResponseDTO responseDTO = ResponseDTO.builder().data(pointDto).build();
      return ResponseEntity.ok().body(responseDTO);
    } catch (Exception e) {
      ResponseDTO responseDTO = ResponseDTO.builder().error("포인트 내역 불러오기 실패!").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }

  @GetMapping("declare")
  public void declare(@AuthenticationPrincipal ApplicationUserPrincipal member) {

  }

}
