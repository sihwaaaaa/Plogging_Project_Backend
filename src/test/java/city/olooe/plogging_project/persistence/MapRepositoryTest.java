package city.olooe.plogging_project.persistence;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.MapEntity;
import city.olooe.plogging_project.model.StopoverEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MapRepositoryTest {
  @Autowired
  MapRepository mapRepository;

  @Test
  @DisplayName("맵 생성 테스트")
  public void createMap() {
    // given
    MapEntity mapEntity = MapEntity.builder().startX(0d).startY(0d).endX(0d).endY(0d).courseName("테스트")
        .courseDetail("테스트내용").addr("테스트주소").distance(0).build();
    // when
    // then
    log.info("{}", mapRepository.save(mapEntity));

  }

}
