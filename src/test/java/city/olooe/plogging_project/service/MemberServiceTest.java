package city.olooe.plogging_project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @DisplayName("회원 생성 테스트")
  @Test
  public void testCreateMember() {
    memberService.create(new MemberEntity("root1234", "123456", "아로하", "root@123.com"));
  }

  @DisplayName("회원 중복 여부 확인")
  @Test
  public void testCheckMember() {
    memberService.checkMember("root1234", "123456");
  }

}
