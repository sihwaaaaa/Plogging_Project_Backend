package city.olooe.plogging_project.model.map;

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
import lombok.ToString;

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
  private Long viaPointId; // pk
  private Double viaX; // 경유지 x좌표
  private Double viaY; // 경유지 x좌표
  private Integer stopoverIdx;
  // @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne
  //@Setter // 교차검증 후 db반영용
  @JoinColumn(name = "mapNo")
  private MapEntity mapEntity;
// @Override
// public String toString() {
//     return "StopoverEntity{" +
//             "stopoverNo=" + stopoverNo +
//             ", stopoverX=" + stopoverX +
//             ", stopoverY=" + stopoverY +
//             ", stopoverIdx=" + stopoverIdx +
//             ", mapEntity=" + mapEntity.getMapNo() +
//             '}';
// }


}