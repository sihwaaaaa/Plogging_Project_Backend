package city.olooe.plogging_project.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.dto.community.BoardDTO;
import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ChallengeMemberDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.security.CustomUserDetails;
import city.olooe.plogging_project.service.ChallengeService;
import city.olooe.plogging_project.service.MemberService;
import city.olooe.plogging_project.service.PointHistoryService;
import city.olooe.plogging_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 박연재
 * 
 * @date 23.06.22
 * 
 * @brief 프로필 관련 기능
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
@Slf4j
public class ProfileController {

  private final MemberService memberService;
  private final ProfileService profileService;

  /**
   * @author 박연재
   * @date 23.06.22
   * @param member
   * @return
   */
  @GetMapping({ "challenge", "plogging", "point", "board" })
  public ResponseEntity<?> myProfile(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    // return
    // responseContent(profileService.searchChallengeDetailByMember(member.getMember()),
    // "챌린저 내역 불러오기 실패!");
    return responseContent(memberService.getMember(member.getMember()), null, "내역 불러오기 실패!");
  }

  @GetMapping("{memberNo}")
  public ResponseEntity<?> oppositeProfile(@PathVariable Long memberNo) {
    return responseContent(null, memberService.getMember(memberNo), "상대 회원분 내역 불러오기 실패!!");
  }

  private ResponseEntity<?> responseContent(MemberEntity member, Optional<MemberEntity> optionalMember,
      String errorMsg) {
    ResponseDTO response = null;
    try {
      MemberDTO memberDTO = new MemberDTO(member);
      response = ResponseDTO.builder().data(memberDTO).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      response = ResponseDTO.builder().error(errorMsg).build();
      return ResponseEntity.badRequest().body(response);
    }

  }
}
