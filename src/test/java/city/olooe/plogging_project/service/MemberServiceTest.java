package city.olooe.plogging_project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.config.WebSecurityConfig;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private WebSecurityConfig securityConfig;

  @DisplayName("회원 생성 테스트")
  @Test
  public void testCreateMember() {
    memberService.create(new MemberEntity("test1234", "123456", "테스트", "root@123.com"));
  }

  @DisplayName("로그인 확인 메서드")
  @Test
  public void testCheckMember() {
    memberService.getByCredentials("root", "1234567", securityConfig.getPasswordEncoder());
  }

  @DisplayName("회원가입 회원 아이디 중복 확인")
  @Test
  public void testCheckValidateUserId() throws Exception {
    memberService.validateWithUserId("pkkj");
  }

}
