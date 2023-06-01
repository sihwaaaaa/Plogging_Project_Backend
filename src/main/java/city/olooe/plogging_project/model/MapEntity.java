package city.olooe.plogging_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_map")
/*
 * @author : 이시화
 * 
 * @date: 2023.06.01
 * 
 * @brief: 추천경로관련 엔티티
 */
public class MapEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long mapNo; // pk
  private double startX; // 시작지점 x좌표
  private double startY; // 시작지점 y좌표
  private double endX; // 끝지점 x좌표
  private double endY; // 끝지점 y좌표
  private String courseName; // 코스명
  private String courseDetail; // 코스설명
  private String addr; // 주소(자치구)
  private int distance; // 총 거리
  private Date time; // 소요시간

  // 1대다 관계 지정
  @OneToMany(mappedBy = "mapEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StopoverEntity> stops = new ArrayList<>(); // 경유지 목록

  /*
   * @author : 이시화
   * 
   * @date: 23.06.01
   * 
   * @param: StopoverEntity
   * 
   * @return: void
   * 
   * @brief: 경로에 경유지 추가 메서드
   */
  public void putStopover(StopoverEntity stopover) {
    this.stops.add(stopover);
  }
}
