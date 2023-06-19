package city.olooe.plogging_project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.dto.friend.FriendDTO;
import city.olooe.plogging_project.dto.map.MapDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.dto.map.StopoverDTO;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.PloggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
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
    @PostMapping("/startPage")
    public void createPlogging(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody PloggingDTO ploggingDTO){
      log.info("{}",ploggingDTO);
      // ploggingService.insertPlogging(ploggingDTO, user.getMember().getMemberNo(),mapDTO);
      return ;
    }
// ObjectMapper objectMapper = new ObjectMapper();

    //받을때 맵 스트링 오브젝트로 받아와서 변경 해야함 json의 시작은 항상 {}
    // @PutMapping("/updateLon")
    // public ResponseEntity<?> updateLon(@RequestBody Map<String,Object> dtos){
    //   List<StopoverDTO> mapDTOs = new ArrayList<>();
    //   Map<Object,Object> map2;
    //   for (int i = 0; i < dtos.size(); i++) {
    //     map2  = (Map<Object, Object>) dtos.get(i+"");
    //     StopoverDTO dto = objectMapper.convertValue(map2, StopoverDTO.class);
    //     // log.info("{}",dto);      
    //     mapDTOs.add(dto);
        
    //   }
    //   log.info("{}",mapDTOs);      
    //   ploggingService.updateData(mapDTOs);
    //   return null;
    // //  return ResponseEntity.ok().body(ResponseDTO.builder().data(dtos).build());
    // }
    // @GetMapping("/stopover")
    // public ResponseEntity<?> readStopoverList() {
      
    //    List<StopoverDTO> dtos = ploggingService.getStopoverList();
    //     // return null;
    //     // if (dto == null) {
    //     //     return ResponseEntity.notFound().build();
    //     // }
    //     return ResponseEntity.ok().body(dtos);
    // }
  }
