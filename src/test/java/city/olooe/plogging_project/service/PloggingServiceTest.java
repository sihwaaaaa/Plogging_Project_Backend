package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.dto.map.StopoverDTO;
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
  @Test
  @DisplayName("경로 조회 테스트")
  @Transactional
  public void searchMap() {
    Pageable pageable = PageRequest.of(0, 5);
    Page<MapDTO> dto = service.searchRoute("동작", pageable);
   System.out.println(dto.toString());
  }
  // @Test
  
// public void updateData() {
//   StopoverDTO dto1 = new StopoverDTO(1459L, 5.23, 1.23, 1, 2L);
//   StopoverDTO dto2 = new StopoverDTO(1455L, 1.23, 1.23, 1, 2L);
//   StopoverDTO dto3 = new StopoverDTO(1456L, 1.23, 1.23, 1, 2L);
//   List<StopoverDTO> dtos = new ArrayList<>();
//   dtos.add(dto3);
//   dtos.add(dto2);
//   dtos.add(dto1);
//   log.info("{}",dtos);  

//   service.updateData(dtos);


    // List<MapEntity> entity = new ArrayList<>();
  
    // for (MapDTO dto : dtos) {
    //  entity.add(dto.toEntity());
    // }
    // log.info("{}",entity);
    
    
  // }
}
