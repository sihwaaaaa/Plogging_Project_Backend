package city.olooe.plogging_project.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 엔티티
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity // 테이블과 링크될 클래스
@DynamicInsert
@DynamicUpdate
@ToString
@Table(name = "tbl_board")
public class BoardEntity {
  @Id // 해당 테이블의 PK 필드
  @GeneratedValue(strategy = GenerationType.AUTO) // PK 생성규칙 표시, auto_increment를 위해서 옵션 추가 필수
  @Column(name = "bno", unique = true, nullable = false)
  private Long bno; // 게시글 번호 : PK

  @Column(nullable = false)
  private String title; // 게시글 제목

  @Column(columnDefinition = "TEXT")
  private String content; // 게시글 내용

  // @CreationTimestamp
  private Date regDate; // 작성일

  // @UpdateTimestamp
  private Date updateDate; // 수정일

  @Column(nullable = false)
  private Integer category; // 게시글 분류(0: community, 1: plogging)

  // private Long writer; // 작성자 : FK

  @ManyToOne(cascade = CascadeType.MERGE, targetEntity = MemberEntity.class) // 관계매핑을 위한 어노테이션
  @JoinColumn(name = "memberNo", updatable = false)
  private MemberEntity memberEntity;

  @Builder // 해당 클래스의 빌더 패턴 클래스 생성, 생성자에 포함된 필드만 빌더에 포함
  public BoardEntity(MemberEntity memberEntity, String title, String content, Integer category) {
    this.memberEntity = memberEntity;
    this.title = title;
    this.content = content;
    this.category = category;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
