package city.olooe.plogging_project.dto.map;

import java.util.Date;

import city.olooe.plogging_project.model.map.PloggingEntity;
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
  @JsonFormat
  private Date ploggingTime; // 이동시간
  @JsonFormat
  private Date regDate; // 플로깅 날짜(insert날짜)
  private Integer distance; // 이동 거리
  private Boolean status; // 성공여부

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
    this.mapNo = entity.getMapNo();
    this.memberNo = entity.getMemberNo();
    this.type = entity.getType();
    this.ploggingTime = entity.getPloggingTime();
    this.regDate = entity.getRegDate();
    this.distance = entity.getDistance();
    this.status = entity.getStatus();
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
        .mapNo(mapNo)
        .memberNo(memberNo)
        .type(type)
        .ploggingTime(ploggingTime)
        .regDate(regDate)
        .distance(distance)
        .status(status)
        .build();
  }
}
