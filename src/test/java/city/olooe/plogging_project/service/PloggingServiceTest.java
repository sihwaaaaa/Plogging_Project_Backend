package city.olooe.plogging_project.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PloggingServiceTest {
  @Autowired
  private PloggingService service;


  @Test
  @DisplayName("map 단일 조회 테스트")
  @Transactional
  public void readMapEntity() {
    MapDTO dto = service.serchByMapNo(1L);
   System.out.println(dto.toString());
  }

}
