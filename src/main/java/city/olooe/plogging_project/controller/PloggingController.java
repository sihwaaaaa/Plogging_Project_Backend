package city.olooe.plogging_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.service.PloggingService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("plogging")
@Slf4j
/**
* @author : 이시화
* @date: 23.06.11
* 
* @brief: plogging관련 컨트롤러
*/
public class PloggingController {
  @Autowired
  private PloggingService ploggingService;

    @GetMapping
    public ResponseEntity<?> readRouteList() {
      
       List<MapDTO> dtos = ploggingService.getMapList();
        // return null;
        // if (dto == null) {
        //     return ResponseEntity.notFound().build();
        // }
        return ResponseEntity.ok().body(dtos);
    }
    @GetMapping("/{mapNo}")
    public ResponseEntity<?> searchByMapNo(@PathVariable("mapNo") Long mapNo) {
        MapDTO dto = ploggingService.serchByMapNo(mapNo);
        log.info("{}",dto);
        // return null;
        // if (dto == null) {
        //     return ResponseEntity.notFound().build();
        // }
        return ResponseEntity.ok().body(dto);
    }
}
