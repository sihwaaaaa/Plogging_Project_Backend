package city.olooe.plogging_project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 엔티티
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_board")
public class BoardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno; // 게시글 번호 : PK

  private String title; // 게시글 제목

  private String content; // 게시글 내용

  // @CreationTimestamp
  private Date regDate; // 작성일

  // @UpdateTimestamp
  private Date updateDate; // 수정일

  private Integer category; // 게시글 분류(0: community, 1: plogging)

  private Long writer; // 작성자 : FK

  @Builder
  public BoardEntity(Long bno, Long writer, String title, String content, Integer category) {
    this.bno = bno;
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.category = category;
  }
}
