package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.member.MemberSearchDTO;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import city.olooe.plogging_project.config.WebSecurityConfig;
import city.olooe.plogging_project.dto.MailCheckDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.security.TokenProvider;
import city.olooe.plogging_project.service.EmailService;
import city.olooe.plogging_project.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author: 박연재
 * 
 * @date: 2023.06.02
 * 
 * @brief: 회원 컨트롤러
 * 
 */
@RestController
@Slf4j
@RequestMapping("member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private EmailService emailService;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private WebSecurityConfig securityConfig;

  @Autowired
  private CustomUserDetails customUserDetails;

  // /**
  // * @author: 박연재
  // * @date: 2023.06.02
  // * @brief: 로그인 페이지
  // * @param param
  // * @return void
  // */
  // @GetMapping(value = "login")
  // public void login(@RequestParam String param) {

  // }

  /**
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 로그인 페이지
   * @param memberDTO
   * @return void
   */
  @PostMapping("signin")
  public ResponseEntity<?> signin(@RequestBody MemberDTO memberDTO) {
    // 1. 아이디, 비밀번호를 통해서 로그인 완료
    // if(memberService.checkMember(dto.getUserId(),dto.getPassword())){
    // MemberEntity entity = MemberDTO.toEntity(dto);
    // return ResponseEntity.ok().body(entity);
    //
    MemberEntity member = memberService.getByCredentials(memberDTO.getUserId(), memberDTO.getPassword(),
        securityConfig.getPasswordEncoder());
    if (member != null) {
      final String token = tokenProvider.userCreateToken(member);
      log.info("{}", token);
      final MemberDTO responseMemberDTO = MemberDTO.builder()
          .memberNo(member.getMemberNo())
          .userId(member.getUserId())
          .email(member.getEmail())
          .gender(member.getGender())
          .address(member.getAddress())
          .birth(member.getBirth())
          .regDate(member.getRegDate())
          .nickName(member.getNickName())
          .userName(member.getUserName())
          .regDate(member.getRegDate())
          .token(token)
          .authList(member.getAuthEntities().stream().map(s -> s.getAuthority().getKey()).collect(Collectors.toList()))
          .build();
      return ResponseEntity.ok().body(responseMemberDTO);
    } else {
      ResponseDTO responseDTO = ResponseDTO.builder().error("로그인 실패!").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }

  @PostMapping("signup")
  public ResponseEntity<?> registerMember(@RequestBody MemberDTO memberDTO) {
    try {
      MemberEntity member = MemberEntity.builder()
          .userId(memberDTO.getUserId())
          .password(securityConfig.getPasswordEncoder().encode(memberDTO.getPassword()))
          .birth(memberDTO.getBirth())
          .userName(memberDTO.getUserName())
          .email(memberDTO.getEmail())
          .address(memberDTO.getAddress())
          .nickName(memberDTO.getNickName())
          .gender(memberDTO.getGender())
          .build();

      memberService.validateWithMember(member, memberDTO);
      MemberEntity registeredMember = memberService.create(member);
      memberService.createAuth(registeredMember);

      MemberDTO responseMemberDTO = MemberDTO.builder()
          .memberNo(registeredMember.getMemberNo())
          .userId(registeredMember.getUserId())
          .password(registeredMember.getPassword())
          .userName(registeredMember.getUserName())
          .birth(registeredMember.getBirth())
          .email(registeredMember.getEmail())
          .address(registeredMember.getAddress())
          .nickName(registeredMember.getNickName())
          .gender(registeredMember.getGender())
          .build();
      return ResponseEntity.ok().body(responseMemberDTO);
    } catch (Exception e) {
      // 유저 정보는 유일해야하므로 MemberDTO 리턴
      ResponseDTO resposneDTO = ResponseDTO.builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(resposneDTO);
    }
  }

  @PostMapping("signup/checkId")
  public ResponseEntity<?> checkUserId(@RequestParam String userId) throws Exception {
    try {
      memberService.validateWithUserId(userId);
      return ResponseEntity.ok().body(userId);
    } catch (Exception e) {
      ResponseDTO resposneDTO = ResponseDTO.builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(resposneDTO);
    }
  }

  @GetMapping("logout")
  public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, response,
        SecurityContextHolder.getContext().getAuthentication());
    // response.
    // return "redirect:/";
  }

  @PostMapping("/signup/emailConfirm")
  public ResponseEntity<?> postMethodName(@RequestBody MailCheckDTO dto) throws Exception {
    String confirm = emailService.sendMessage(dto.getEmail());
    if (confirm == null) {
      return ResponseEntity.badRequest().body("null");
    }
    return ResponseEntity.ok().body(confirm);
  }

  /**
   * @Author 천은경
   * @Date 23.06.15
   * @param keyword
   * @return 회원 리스트
   * @Breif userId로 회원 검색
   */
  @GetMapping("/search")
  public ResponseEntity<?> searchMember(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestParam String keyword,
                                        @PageableDefault(sort = "memberNo", size = 5
                                        , direction = Sort.Direction.DESC) Pageable pageable) {

    Page<MemberSearchDTO> memberDTOS = memberService.searchMember(user, keyword, pageable);

    return ResponseEntity.ok().body(ResponseDTO.builder().data(memberDTOS).build());
  }

}
