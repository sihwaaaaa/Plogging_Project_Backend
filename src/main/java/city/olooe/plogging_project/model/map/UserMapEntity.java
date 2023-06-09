package city.olooe.plogging_project.model.map;

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
@Table(name = "tbl_usermap")
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: 회원과 경로를 연결해주는 브릿지 테이블(다대다관계)
 */
public class UserMapEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userMapNo;// pk
  private Long mapNo;// 지도번호 fk
  private Long memberNo;// 회원번호 fk

}
