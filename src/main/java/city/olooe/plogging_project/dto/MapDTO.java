package city.olooe.plogging_project.dto;

import java.util.Date;

import city.olooe.plogging_project.model.MapEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: MapDTO
 */
public class MapDTO {
  private double startX;
  private double startY;
  private double endX;
  private double endY;
  private String courseName;
  private String courseDetail;
  private String addr;
  private int distance;
  private Date time;

  /*
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
    this.startX = entity.getStartX();
    this.startY = entity.getStartY();
    this.endX = entity.getEndX();
    this.endX = entity.getEndY();
    this.courseName = entity.getCourseName();
    this.courseDetail = entity.getCourseDetail();
    this.addr = entity.getAddr();
    this.distance = entity.getDistance();
    this.time = entity.getTime();
  }

  /*
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
  public static MapEntity toEntity(final MapDTO mapDTO) {
    return MapEntity.builder()
        .startX(mapDTO.getStartX())
        .startY(mapDTO.getStartY())
        .endX(mapDTO.getEndX())
        .endY(mapDTO.getEndY())
        .courseName(mapDTO.getCourseName())
        .courseDetail(mapDTO.getCourseDetail())
        .addr(mapDTO.getAddr())
        .distance(mapDTO.getDistance())
        .time(mapDTO.getTime())
        .build();
  }

}