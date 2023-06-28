package city.olooe.plogging_project.dto.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.MapRepository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: PloggingDTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PloggingDTO {
  private Long ploggingNo;// pk
  // private Long mapNo;// fk
  private Long memberNo;// fk
  private String type; // 유형 (제자리시작 / 목표지설정 / 추천경로 / 챌린지경로)
  private Integer ploggingTime; // 이동시간
  @JsonFormat
  private Date regDate; // 플로깅 날짜(insert날짜)
  private Double distance; // 이동 거리
  private Boolean status; // 성공여부

  // 맵에 대한 정보
  private MapDTO mapDTO;

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: PloggingEntity
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  
  public PloggingDTO(final PloggingEntity entity) {
    this.ploggingNo = entity.getPloggingNo();
    // this.mapNo = entity.getMapNo();
    this.memberNo = entity.getMember().getMemberNo();
    this.type = entity.getType();
    this.ploggingTime = entity.getPloggingTime();
    this.regDate = entity.getRegDate();
    this.distance = entity.getDistance();
    this.status = entity.getStatus();

    // 연재 - 맵에 대한 정보를 불러옴 ( 프로필에 쓰일 코스 이름을 추출하기 위해서 )
   this.mapDTO = new MapDTO(entity.getMapEntity());
  }
  public PloggingDTO( Long ploggingNo,Long memberNo,String type, Integer ploggingTime, Date regDate, Double distance,Boolean status,MapEntity mapEntity){

    this.ploggingNo = ploggingNo;
    this.memberNo = memberNo;
    this.type = type;
    this.ploggingTime = ploggingTime;
    this.regDate = regDate;
    this.distance = distance;
    this.status = status;
  
    // 연재 - 맵에 대한 정보를 불러옴 ( 프로필에 쓰일 코스 이름을 추출하기 위해서 )
   this.mapDTO =new MapDTO(mapEntity.getMapNo(),mapEntity.getStartX(),mapEntity.getStartY(),mapEntity.getEndX(),mapEntity.getEndY());
  }
  


  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: PloggingDTO
   * @return: void
   * 
   * @brief: DTO -> entity
   */
  public PloggingEntity toEntity() {
    return PloggingEntity.builder()
        .ploggingNo(ploggingNo)
        .mapEntity(mapDTO.toEntity())
        .member(MemberEntity.builder().memberNo(memberNo).build())
        .type(type)
        .ploggingTime(ploggingTime)
        .regDate(regDate)
        .distance(distance)
        .status(status)
        .build();
  }
  public PloggingEntity toEntityStops() {
    return PloggingEntity.builder()
        .ploggingNo(ploggingNo)
        .mapEntity(mapDTO.toEntityNotStops())
        .member(MemberEntity.builder().memberNo(memberNo).build())
        .type(type)
        .ploggingTime(ploggingTime)
        .regDate(regDate)
        .distance(distance)
        .status(status)
        .build();
  }
}
