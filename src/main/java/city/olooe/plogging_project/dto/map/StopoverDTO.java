package city.olooe.plogging_project.dto.map;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: StopoverDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopoverDTO {
  private Long stopoverNo; // pk
  private Double stopoverX; // 경유지 x좌표
  private Double stopoverY; // 경유지 x좌표
  private Integer stopoverIdx; // 경유지 순서
  private MapEntity mapEntity; // 경유지를 가지고 있는 맵

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: StopoverEntity
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  public StopoverDTO(final StopoverEntity entity) {
    this.stopoverNo = entity.getStopoverNo();
    this.stopoverX = entity.getStopoverX();
    this.stopoverY = entity.getStopoverY();
    this.stopoverIdx = entity.getStopoverIdx();
    this.mapEntity = entity.getMapEntity();
  }

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: StopoverDTO
   * @return: StopoverEntity
   * 
   * @brief: DTO -> Entity
   */

  public static StopoverEntity toEntity(final StopoverDTO stopoverDTO) {
    return StopoverEntity.builder()
        .stopoverNo(stopoverDTO.getStopoverNo())
        .stopoverX(stopoverDTO.getStopoverX())
        .stopoverY(stopoverDTO.getStopoverY())
        .stopoverIdx(stopoverDTO.getStopoverIdx())
        .mapEntity(stopoverDTO.getMapEntity())
        .build();
  }
}
