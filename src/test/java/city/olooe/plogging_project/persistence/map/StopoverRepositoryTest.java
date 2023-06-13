package city.olooe.plogging_project.persistence.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import city.olooe.plogging_project.persistence.map.StopoverRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: 경유지 테스트 클래스
 */
public class StopoverRepositoryTest {
  @Autowired
  StopoverRepository repository;

  @Test
  @DisplayName("경유지 단일 생성 테스트")
  public void createStopover() {

    // given
    StopoverEntity entity = StopoverEntity.builder().stopoverX(123.1).stopoverY(123.1)
        .mapEntity(MapEntity.builder().mapNo(1L).build())
        .stopoverIdx(1)
        .build();
    // then
    log.info("{}", repository.save(entity));
  }
}
