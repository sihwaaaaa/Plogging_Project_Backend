package city.olooe.plogging_project.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.security.CustomUserDetails;
import city.olooe.plogging_project.service.PointHistoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("profile")
@Slf4j
public class ProfileController {

  @Autowired
  private PointHistoryService pointService;

  @GetMapping("challenge") 
  public void challenge(@AuthenticationPrincipal ApplicationUserPrincipal member){

  }
  @GetMapping("plogging") 
  public void plogging(@AuthenticationPrincipal ApplicationUserPrincipal member){

  }
  @GetMapping("myBoard") 
  public void myBoard(@AuthenticationPrincipal ApplicationUserPrincipal member){

  }
  @GetMapping("point") 
  public ResponseEntity<?> point(@AuthenticationPrincipal ApplicationUserPrincipal member){

    // try{
    //   List<PointHistoryEntity> pointList = pointService.GetMemberList(member.getMember(), RewardTypeStatus.Donation.getKey());
    //   List<PointHistoryDTO> pointDto = pointList.stream().map(PointHistoryDTO::new).collect(Collectors.toList());
    //   log.info("{}", pointList);
    //   // log.info("{}", o.get(0).getPointNo());
    //   // log.info("{}", pointDto.get(0).getPointNo());
    //   ResponseDTO responseDTO = ResponseDTO.builder().data(pointDto).build();
    //   return ResponseEntity.ok().body(pointList);
    // }catch(Exception e){
    //   ResponseDTO responseDTO = ResponseDTO.builder().error("포인트 내역 불러오기 실패!").build();
    //   return ResponseEntity.badRequest().body(responseDTO);
    // }
    return null;
  }
  @GetMapping("declare") 
  public void declare(@AuthenticationPrincipal ApplicationUserPrincipal member){

  }

}
