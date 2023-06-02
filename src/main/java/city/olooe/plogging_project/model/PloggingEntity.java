package city.olooe.plogging_project.model;

import java.util.Date;

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
@Table(name = "tbl_plogging")
/*
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: 플로깅 엔티티
 */
public class PloggingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ploggingNo;// pk
  private long userMapNo; // 브릿지 테이블pk
  private String type; // 유형 (제자리시작 / 목표지설정 / 추천경로 / 챌린지경로)
  private Date ploggingTime; // 이동시간
  private Date regDate; // 플로깅 날짜(insert날짜)
  private int distance; // 이동 거리
  private boolean status; // 성공여부

}