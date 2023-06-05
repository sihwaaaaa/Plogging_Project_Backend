package city.olooe.plogging_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.service.MemberService;
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
@Controller
@RequestMapping("member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @GetMapping(value = "login")
  public void login(@RequestParam String param) {

  }

  @GetMapping(value = "signup")
  public String signUp(@RequestParam String param) {
    return null;
  }

  @PostMapping(value = "path")
  public String postMethodName(@RequestBody MemberDTO memberDto) {
    return null;
  }

}
