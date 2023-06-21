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
  @GetMapping({"challenge", "plogging", "point"})
  public ResponseEntity<?> challenge(@AuthenticationPrincipal ApplicationUserPrincipal member) {
    // return responseContent(profileService.searchChallengeDetailByMember(member.getMember()), "챌린저 내역 불러오기 실패!");
    return responseContent(memberService.getMember(member.getMember()), "챌린저 내역 불러오기 실패!");
  }

  // @GetMapping("plogging")
  // public ResponseEntity<?> plogging(@AuthenticationPrincipal ApplicationUserPrincipal member) {
  //   return responseContent(profileService.searchPloggingByMember(member.getMember()), "플로깅 내역 조회 실패!");
  // }

  // @GetMapping("board")
  // public ResponseEntity<?> myBoard(@AuthenticationPrincipal ApplicationUserPrincipal member) {
  //   try {
  //     List<BoardEntity> boardEntities = profileService.searchBoardByMember(member.getMember());
  //     log.info("{}", boardEntities);
  //     List<BoardDTO> boardDTOs = boardEntities.stream().map(BoardDTO::new).collect(Collectors.toList());
  //     ResponseDTO response = ResponseDTO.builder().data(boardDTOs).build();
  //     return ResponseEntity.ok().body(response);
  //   } catch (Exception e) {
  //     ResponseDTO response = ResponseDTO.builder().error("실패!").build();
  //     return ResponseEntity.badRequest().body(response);
  //   }
  // }

  // @GetMapping("point")
  // public ResponseEntity<?> point(@AuthenticationPrincipal ApplicationUserPrincipal member) {
  //   return responseContent(profileService.searchPointHistoryByMember(member.getMember()), "포인트 내역 조회 실패!");
  // }

  // @GetMapping("declare")
  // public void declare(@AuthenticationPrincipal ApplicationUserPrincipal member) {

  // }

  private ResponseEntity<?> responseContent(MemberEntity member, String errorMsg){
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
