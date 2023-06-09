package city.olooe.plogging_project.dto;

import java.time.LocalDateTime;

import city.olooe.plogging_project.model.BoardEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author : 김성진
 * @date : 2023.06.02
 * @param :
 * @return : boardBuild
 * @brief : BoardDTO를 BoardEntity로 변환
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  private MemberEntity memberEntity; // 작성하는 회원
  private String userId; // 작성한 회원 아이디
  private Long bno; // 게시글 번호(PK)
  private String title; // 게시글 제목
  private String content; // 게시글 내용
  private LocalDateTime regDate; // 게시글 작성일
  private LocalDateTime updateDate; // 게시글 수정일
  private Integer category; // 게시글 카테고리(0 : 커뮤니티, 1 : 플로깅)

  /**
   * @author : 김성진
   * @date: 23.06.05
   * 
   * @param: -
   * @return: entity
   * 
   * @brief: DTO -> entity
   */
  public BoardEntity toEntity() {
    return BoardEntity.builder().memberEntity(memberEntity).title(title).content(content).category(category).build();
  }

  /**
   * @author : 김성진
   * @date: 23.06.05
   * 
   * @param: memberEntity, title, content, category
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  @Builder
  public BoardDTO(MemberEntity memberEntity, String title, String content, Integer category) {
    this.memberEntity = memberEntity;
    this.title = title;
    this.content = content;
    this.category = category;
  }

  /**
   * @author : 김성진
   * @date: 23.06.05
   * 
   * @param: title, content
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  @Builder
  public BoardDTO(String title, String content) {
    this.title = title;
    this.content = content;
  }

  /**
   * @author : 김성진
   * @date: 23.06.05
   * 
   * @param: boardEntity
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  @Builder
  public BoardDTO(BoardEntity boardEntity) {
    this.bno = boardEntity.getBno();
    this.userId = boardEntity.getMemberEntity().getUserId();
    this.title = boardEntity.getTitle();
    this.content = boardEntity.getContent();
  }

}
