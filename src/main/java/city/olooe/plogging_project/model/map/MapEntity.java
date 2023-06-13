package city.olooe.plogging_project.model.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : 이시화
 * 
 * @date: 2023.06.01
 * 
 * @brief: 추천경로관련 엔티티
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@ToString
@EqualsAndHashCode
@Entity(name = "tbl_map")
public class MapEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mapNo; // pk
  private Double startX; // 시작지점 x좌표
  private Double startY; // 시작지점 y좌표
  private Double endX; // 끝지점 x좌표
  private Double endY; // 끝지점 y좌표image.png
  private String courseName; // 코스명
  private String courseDetail; // 코스설명
  private String addr; // 주소(자치구)
  private Double distance; // 총 거리
  private Integer time; // 소요시간

  // 1대다 관계 지정 추후 리스트를 통해 관리할 경우 사용
  // @OneToMany(mappedBy = "mapEntity", cascade = CascadeType.ALL, orphanRemoval =
  // true)
  // private List<StopoverEntity> stops = new ArrayList<>(); // 경유지 목록

}
