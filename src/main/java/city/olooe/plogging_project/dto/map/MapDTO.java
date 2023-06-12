package city.olooe.plogging_project.dto.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: MapDTO
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MapDTO {
  private Long mapNo;
  private Double startX;
  private Double startY;
  private Double endX;
  private Double endY;
  private String courseName;
  private String courseDetail;
  private String addr;
  private Double distance;
  private Integer time;
  private List<StopoverDTO> stops;// = new ArrayList<>();

  /**
   * @author : 이시화
   * 
   * @date: 23.06.01
   * 
   * @param: MapEntity
   * 
   * @return: void
   * 
   * @brief: Entity를 DTO로 변환
   */
  public MapDTO(final MapEntity entity) {
    this.mapNo = entity.getMapNo();
    this.startX = entity.getStartX();
    this.startY = entity.getStartY();
    this.endX = entity.getEndX();
    this.endY = entity.getEndY();
    this.courseName = entity.getCourseName();
    this.courseDetail = entity.getCourseDetail();
    this.addr = entity.getAddr();
    this.distance = entity.getDistance();
    this.time = entity.getTime();
     this.stops = entity.getStops().stream().map(StopoverDTO::new).collect(Collectors.toList());
  }

  /**
   * @author : 이시화
   * 
   * @date: 23.06.01
   * 
   * @param: MapDTO
   * 
   * @return: MapEntity
   * 
   * @brief: MapDTO를 MapEntity로 변환
   */
  // public static MapEntity toEntity(final MapDTO mapDTO) {
  //   return MapEntity.builder()
  //       .mapNo(mapDTO.getMapNo())
  //       .startX(mapDTO.getStartX())
  //       .startY(mapDTO.getStartY())
  //       .endX(mapDTO.getEndX())
  //       .endY(mapDTO.getEndY())
  //       .courseName(mapDTO.getCourseName())
  //       .courseDetail(mapDTO.getCourseDetail())
  //       .addr(mapDTO.getAddr())
  //       .distance(mapDTO.getDistance())
  //       .time(mapDTO.getTime())
  //       .stops(mapDTO.getStops())
  //       .build();
  // }
}