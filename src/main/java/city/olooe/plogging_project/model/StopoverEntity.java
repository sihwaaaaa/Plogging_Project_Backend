package city.olooe.plogging_project.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : 이시화
 * @date: 23.06.01
 * 
 * @brief: 경유지 엔티티
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity(name = "tbl_stopover")
public class StopoverEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stopoverNo; // pk
  private Double stopoverX; // 경유지 x좌표
  private Double stopoverY; // 경유지 x좌표
  private Integer stopoverIdx;
  @ManyToOne
  @Setter // 교차검증 후 db반영용
  @JoinColumn(name = "mapNo")
  private MapEntity mapEntity;


  public String toString() {
    return String.format("tmp : %s, mapNo : ", mapEntity.getMapNo());
  }

}