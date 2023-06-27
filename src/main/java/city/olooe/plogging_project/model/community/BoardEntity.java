package city.olooe.plogging_project.model.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import city.olooe.plogging_project.dto.AttachDTO;
import city.olooe.plogging_project.model.AttachEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 엔티티
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 테이블과 링크될 클래스
@DynamicInsert
@DynamicUpdate
@Builder
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

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date regDate;// 작성일
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateDate; // 수정일

  @Setter
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BoardCategory category; // 게시글 분류(0: community, 1: plogging)

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class) // 관계매핑을 위한 어노테이션
  @JoinColumn(name = "memberNo", updatable = false)
  private MemberEntity memberNo; // FK(tbl_member)

  @Setter
  @OneToOne(targetEntity = PloggingEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ploggingNo", referencedColumnName = "ploggingNo")
  private PloggingEntity ploggingNo;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bno", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<ReplyEntity> replys = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "bno", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<AttachEntity> attaches = new ArrayList<>();


  public void update(String title, String content, Date upDatedate) {
    this.title = title;
    this.content = content;
    this.updateDate = upDatedate;
  }
}
