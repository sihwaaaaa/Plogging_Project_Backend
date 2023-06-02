package city.olooe.plogging_project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "tbl_board")
public class BoardEntity {
  @Id
  @GeneratedValue
  private Long bno; // 게시글 번호 : PK

  private String title; // 게시글 제목

  private String content; // 게시글 내용

  @CreatedDate
  private Date regDate; // 작성일

  @LastModifiedDate
  private Date updateDate; // 수정일

  private Integer category; // 게시글 분류(0: community, 1: plogging)

  private Long writer; // 작성자 : FK

  @Builder
  public BoardEntity(Long bno, Long writer, String title, String content) {
    this.bno = bno;
    this.writer = writer;
    this.title = title;
    this.content = content;
  }
}
