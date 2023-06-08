package city.olooe.plogging_project.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.08
 * 
 * @brief : 파일첨부 관련 엔티티
 */

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "tbl_attach")
public class PhotoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "attachNo")
  private Long attachNo; // 파일 첨부 번호(PK)

  @JoinColumn(name = "bno")
  @OneToOne
  private BoardEntity boardEntity; // 게시글 번호(FK)

  @JoinColumn(name = "chNo")
  @OneToOne
  private ChallengeEntity challengeEntity; // 챌린지 번호(FK)

  @JoinColumn(name = "badgeNo")
  @OneToOne
  private BadgeEntity badgeEntity; // 뱃지 번호(FK)

  private String uuid; // 파일 이름

  private String path; // 파일 경로

  private Long fileSize; // 파일 사이즈

  // public void setBoard(BoardEntity boardEntity) {
  // this.boardEntity = boardEntity;

  // if(!boardEntity.
  // }

}
