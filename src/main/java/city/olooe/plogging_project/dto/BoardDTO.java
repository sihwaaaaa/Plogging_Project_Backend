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
  private MemberEntity memberEntity;
  private String userId;
  private Long bno;
  private String title;
  private String content;
  private LocalDateTime regDate;
  private LocalDateTime updateDate;
  private Integer category;

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
