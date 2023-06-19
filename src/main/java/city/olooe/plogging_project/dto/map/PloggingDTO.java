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
  private Long userMapNo; // 브릿지 테이블pk
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
    this.userMapNo = entity.getUserMapNo();
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
  public static PloggingEntity toEntity(final PloggingDTO ploggingDTO) {
    return PloggingEntity.builder()
        .ploggingNo(ploggingDTO.getPloggingNo())
        .userMapNo(ploggingDTO.getUserMapNo())
        .type(ploggingDTO.getType())
        .ploggingTime(ploggingDTO.getPloggingTime())
        .regDate(ploggingDTO.getRegDate())
        .distance(ploggingDTO.getDistance())
        .status(ploggingDTO.getStatus())
        .build();
  }
}
