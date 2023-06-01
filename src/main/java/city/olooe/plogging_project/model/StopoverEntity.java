package city.olooe.plogging_project.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* @author : 이시화
* @date: 23.06.01
* 
* @brief: 경유지 엔티티
*/
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_stopover")
public class StopoverEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long stopoverNo; // pk
  private double stopoverX; // 경유지 x좌표
  private double stopoverY; // 경유지 x좌표
  private int stopoverIdx; // 경유지 순서

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mapNo")
  private MapEntity mapEntity; // mapNo를 통해 조회 - MapEntity가지고 있음
}