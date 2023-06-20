package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.dto.map.StopoverDTO;
import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import city.olooe.plogging_project.persistence.MapRepository;
import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.StopoverRepository;
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
  @Autowired
  private StopoverRepository stopoverRepository;
  @Autowired
  private PloggingRepository ploggingRepository;
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
  @Transactional
  public List<MapDTO> getMapList() {
    List<MapEntity> entity = repository.findAll();
    List<MapDTO> dtos = entity.stream().map(MapDTO::new).collect(Collectors.toList());
    return dtos;
  }
  @Transactional
  public void insertPlogging(PloggingDTO dto, Long memberNo,MapDTO mapDTO) {
    
    if (mapDTO.getMapNo() == null) {
    Long  mapNo = repository.save(mapDTO.toEntityNotStops()).getMapNo();
      dto.setMapNo(mapNo);
    }else{
      dto.setMapNo(mapDTO.getMapNo());
    }
    dto.setMemberNo(memberNo);
    ploggingRepository.save(dto.toEntity());
    
    
    
  }


  // @Transactional
  // public List<StopoverDTO> getStopoverList() {
  //   List<StopoverEntity> entity = stopoverRepository.findAll();
  //   List<StopoverDTO> dtos = entity.stream().map(StopoverDTO::new).collect(Collectors.toList());
  //   return dtos;
  // }
  // @Transactional
  // public void updateData(List<StopoverDTO> dtos) {
  //   List<StopoverEntity> entity = new ArrayList<>();
  //   for (StopoverDTO dto : dtos) {
  //     entity.add(dto.toEntity());
  //   }
  //   stopoverRepository.saveAll(entity);

  // }
}
