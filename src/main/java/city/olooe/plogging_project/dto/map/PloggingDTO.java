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
  private Long mapNo;// fk
  private Long memberNo;// fk
  private String type; // 유형 (제자리시작 / 목표지설정 / 추천경로 / 챌린지경로)
  private Integer ploggingTime; // 이동시간
  @JsonFormat
  private Date regDate; // 플로깅 날짜(insert날짜)
  private Double distance; // 이동 거리
  private Boolean status; // 성공여부

  // 맵에 대한 정보
  private MapDTO map;

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
    this.map = new MapDTO(entity.getMapNo());
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
        .mapNo(MapEntity.builder().mapNo(mapNo).build())
        .member(MemberEntity.builder().memberNo(memberNo).build())
        .type(type)
        .ploggingTime(ploggingTime)
        .regDate(regDate)
        .distance(distance)
        .status(status)
        .build();
  }
}
