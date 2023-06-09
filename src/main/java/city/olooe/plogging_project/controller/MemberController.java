package city.olooe.plogging_project.controller;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.config.WebSecurityConfig;
import city.olooe.plogging_project.dto.MailCheckDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.security.TokenProvider;
import city.olooe.plogging_project.service.EmailService;
import city.olooe.plogging_project.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
   * @param param
   * @return void
   */
  @PostMapping("signin")
  public ResponseEntity<?> signin(@RequestBody MemberDTO memberDTO) {
    // 1. 아이디, 비밀번호를 통해서 로그인 완료
    // if(memberService.checkMember(dto.getUserId(),dto.getPassword())){
    // MemberEntity entity = MemberDTO.toEntity(dto);
    // return ResponseEntity.ok().body(entity);
    // }
    MemberEntity member = memberService.getByCredentials(memberDTO.getUserId(), memberDTO.getPassword(),
        securityConfig.getPasswordEncoder());

    if (member != null) {
      final String token = tokenProvider.create(member);
      log.info("{}", token);
      final MemberDTO responseMemberDTO = MemberDTO.builder()
          .memberNo(member.getMemberNo())
          .userId(member.getUserId())
          .token(token)
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
      if (memberDTO == null || memberDTO.getPassword() == null) {
        throw new Exception("유효하지 않는 패스워드 값");
      }
      System.out.println(memberDTO);
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

      MemberEntity registeredMember = memberService.create(member);
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

  @PostMapping("/signup/emailConfirm")
  public ResponseEntity<?> postMethodName(@RequestBody MailCheckDTO dto) throws Exception {
    String confirm = emailService.sendMessage(dto.getEmail());
    if (confirm == null) {
      return ResponseEntity.badRequest().body("null");
    }
    return ResponseEntity.ok().body(confirm);
  }

}
