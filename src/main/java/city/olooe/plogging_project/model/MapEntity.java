package city.olooe.plogging_project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "map")
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

}
