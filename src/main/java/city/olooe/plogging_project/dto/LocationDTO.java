package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: LocationDTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDTO {
  private Long locationNo; // pk
  private Double locationX; // 거점 x좌표
  private Double locationY; // 거점 x좌표
  private String type; // 유형(거점 / 쓰레기통)
  private String detail; // 상세설명 (거점-상호명/쓰레기통-상세위치)
  private String addr; // 위치 주소

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: LocationEntity
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  public LocationDTO(final LocationEntity entity) {
    this.locationNo = entity.getLocationNo();
    this.locationX = entity.getLocationX();
    this.locationY = entity.getLocationY();
    this.type = entity.getType();
    this.detail = entity.getDetail();
    this.addr = entity.getAddr();

  }

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: LocationDTO
   * @return: LocationEntity
   * 
   * @brief: DTO -> Entity
   */
  public static LocationEntity toEntity(final LocationDTO locationDTO) {
    return LocationEntity.builder()
        .locationNo(locationDTO.getLocationNo())
        .locationX(locationDTO.getLocationX())
        .locationY(locationDTO.getLocationY())
        .type(locationDTO.getType())
        .detail(locationDTO.getDetail())
        .addr(locationDTO.getAddr())
        .build();
  }
}
