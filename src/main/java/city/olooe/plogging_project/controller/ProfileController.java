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
import org.springframework.web.bind.annotation.RequestBody;
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
   * @return ResponseEntity
   */
  @GetMapping
  public ResponseEntity<?> myProfile(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    // return
    // responseContent(profileService.searchChallengeDetailByMember(member.getMember()),
    // "챌린저 내역 불러오기 실패!");
    return resProfile(memberService.getMember(member.getMember()), null, "내역 불러오기 실패!");
  }

  @GetMapping("{memberNo}")
  public ResponseEntity<?> oppositeProfile(@PathVariable Long memberNo) {
    return resProfile(null, memberService.getMember(memberNo), "상대 회원분 내역 불러오기 실패!!");
  }

  /**
   * 
   * @author 박연재
   * @date 23.06.23
   * @brief 회원 정보 수정 페이지
   * @param member
   * @return ResponseEntity
   */
  @GetMapping("edit")
  public ResponseEntity<?> editProfilePage(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    ResponseDTO<?> response = null;
    try {
      MemberDTO memberDTO = new MemberDTO(memberService.getMember(member.getMember()));
      response = ResponseDTO.builder().data(memberDTO).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      response = ResponseDTO.builder().error("회원을 불러오지 못함").build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  /**
   * @author 박연재
   * @date 23.06.23
   * @brief 회원 정보 수정 로직 메서드
   * @param dto
   * @return ResponseEntity
   */
  @PostMapping("edit")
  public ResponseEntity<?> editProfile(@RequestBody MemberDTO dto) {
    return resEditProfile(dto);
  }

  private ResponseEntity<?> resEditProfile(MemberDTO dto) {
    ResponseDTO<?> response = null;
    try {
      memberService.modify(dto);
      response = ResponseDTO.builder().data(dto).build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      response = ResponseDTO.builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  private ResponseEntity<?> resProfile(MemberEntity member, Optional<MemberEntity> optionalMember,
      String errorMsg) {
    ResponseDTO<?> response = null;
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
