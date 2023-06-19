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
  private Long viaPointId; // pk
  private Double viaX; // 경유지 x좌표
  private Double viaY; // 경유지 x좌표
  private Integer stopoverIdx; // 경유지 순서
  private Long mapNo; // 경유지를 가지고 있는 맵

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
    this.viaPointId = entity.getViaPointId();
    this.viaX = entity.getViaX();
    this.viaY = entity.getViaY();
    this.stopoverIdx = entity.getStopoverIdx();
    this.mapNo = entity.getMapEntity().getMapNo();
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

  public  StopoverEntity toEntity() {
    return StopoverEntity.builder()
        .viaPointId(viaPointId)
        .viaX(viaX)
        .viaY(viaY)
        .stopoverIdx(stopoverIdx)
        .mapEntity(MapEntity.builder().mapNo(mapNo).build())
        .build();
  }
}
