package city.olooe.plogging_project.persistence.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.PloggingRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: ploggingRepository 테스트 클래스
 */
public class PloggingRepositoryTest {
  @Autowired
  private PloggingRepository ploggingRepository;

  @Test
  @DisplayName("플로깅 생성 테스트")
  public void createPlogging() {
    PloggingEntity ploggingEntity = PloggingEntity.builder().userMapNo(1L).type("제자리시작").ploggingTime(null).distance(0)
        .status(true).build();
    PloggingEntity ploggingEntity2 = PloggingEntity.builder().userMapNo(1L).type("제자리시작").ploggingTime(null).distance(0)
        .build();
    log.info("{}", ploggingRepository.save(ploggingEntity2));
  }
}
