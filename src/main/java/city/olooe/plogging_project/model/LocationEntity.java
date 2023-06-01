package city.olooe.plogging_project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_location")
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: 거점 위치 엔티티
 */
public class LocationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long locationNo; // pk
  private double locationX; // 거점 x좌표
  private double locationY; // 거점 x좌표
  private String type; // 유형(거점 / 쓰레기통)
  private String detail; // 상세설명 (거점-상호명/쓰레기통-상세위치)
  private String addr; // 위치 주소

}
