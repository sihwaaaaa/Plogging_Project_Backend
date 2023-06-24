package city.olooe.plogging_project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProfileServiceTest {
  @Autowired
  private ProfileService profileService;

  @Autowired
  private MemberRepository memberRepository;

  // @Test
  // public void testMyChallenges() {
  // log.info("{}",
  // profileService.searchByMember(memberRepository.findByUserId("pkkj")));

  // }
}
