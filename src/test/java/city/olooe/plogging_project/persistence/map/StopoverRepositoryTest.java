package city.olooe.plogging_project.persistence.map;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import city.olooe.plogging_project.persistence.StopoverRepository;
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
  @Autowired
  MapRepository mapRepository;

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
  
  @Test
  @DisplayName("경유지 조회 테스트")
  public void readStopover() {
    // List<StopoverEntity> entities = repository.findByMapEntity(MapEntity.builder().build());
    // for (StopoverEntity stopoverEntity : entities) {
    //   System.out.println(stopoverEntity.getStopoverNo());
    // }
  }
}
