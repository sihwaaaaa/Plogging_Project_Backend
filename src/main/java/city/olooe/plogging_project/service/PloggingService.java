package city.olooe.plogging_project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import lombok.extern.slf4j.Slf4j;

/**
* @author : 이시화
* @date: 23.06.11
* 
* @brief: 비즈니스 계층의 플로깅 서비스
*/
@Service
@Slf4j
public class PloggingService {
  
  @Autowired
  private MapRepository repository;// map jpa구현체
  
  /**
  * @author : 이시화
  * @date: 23.06.11
  * 
  * @param: mapNo
  * @return: MapDTO
  * 
  * @brief: 경로 단일 조회
  */
  @Transactional
  
  public MapDTO serchByMapNo(final Long mapNo) {
     MapEntity entity = repository.findById(mapNo).orElseThrow(()->new RuntimeException(""));
     MapDTO dto = new MapDTO(entity);
    return dto;
  }
  /**
  * @author : 이시화
  * @date: 23.06.12
  * 
  * @param: void
  * @return: mapEntityList
  * 
  * @brief: 전체 경로리스트를 반환
  */
  public List<MapDTO> getMapList() {
    List<MapEntity> entity = repository.findAll();
    List<MapDTO> dtos = entity.stream().map(MapDTO::new).collect(Collectors.toList());
    return dtos;
  }
}
