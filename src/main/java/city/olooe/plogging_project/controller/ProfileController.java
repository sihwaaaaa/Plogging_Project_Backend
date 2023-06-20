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

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.security.CustomUserDetails;
import city.olooe.plogging_project.service.ChallengeService;
import city.olooe.plogging_project.service.PointHistoryService;
import city.olooe.plogging_project.service.ProfileService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("profile")
@Slf4j
public class ProfileController {

  @Autowired
  private PointHistoryService pointService;

  @Autowired
  private ChallengeService challengeService;

  @Autowired
  private ProfileService profileService;

  @GetMapping("challenge")
  public ResponseEntity<?> challenge(@AuthenticationPrincipal ApplicationUserPrincipal member) {

    // 해당 회원의 모든 챌린지들 조회 ( 페이져 = false )
    try {
      List<ChallengeEntity> challengeEntities = profileService.searchByMember(member.getMember());

      // List<> challengeEntities = profileService.searchByMember(member.getMember());
      List<ChallengeDTO> challengeDTOs = challengeEntities.stream().map(ChallengeDTO::new).collect(Collectors.toList());
      // System.out.println(challengeDTOs);
      ResponseDTO response = ResponseDTO.builder().data(challengeDTOs).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ResponseDTO response = ResponseDTO.builder().error("실패!").build();
      return ResponseEntity.ok().body(response);
    }
  }

  @GetMapping("plogging")
  public void plogging(@AuthenticationPrincipal ApplicationUserPrincipal member) {

  }

  @GetMapping("myBoard")
  public void myBoard(@AuthenticationPrincipal ApplicationUserPrincipal member) {

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
