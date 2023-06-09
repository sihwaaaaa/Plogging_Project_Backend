package city.olooe.plogging_project.persistence.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.map.UserMapEntity;
import city.olooe.plogging_project.persistence.map.UserMapRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: userMap브릿지테이블 테스트 클래스
 */
public class UserMapRepositoryTest {
  @Autowired
  UserMapRepository repository;

  @Test
  @DisplayName("브릿지테이블 조회 테스트")
  public void createUserMap() {
    // given
    UserMapEntity userMapEntity = UserMapEntity.builder().mapNo(1L).memberNo(1L).build();
    // then
    log.info("{}", repository.save(userMapEntity));
  }
}
