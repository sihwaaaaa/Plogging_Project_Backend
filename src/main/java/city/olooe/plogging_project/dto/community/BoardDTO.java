package city.olooe.plogging_project.dto.community;

import java.util.Date;

import city.olooe.plogging_project.model.community.BoardCategory;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
public class BoardDTO {
  private Long memberNo; // 작성하는 회원
  private String userId; // 작성한 회원 아이디
  private Long bno; // 게시글 번호(PK)
  private String title; // 게시글 제목
  private String content; // 게시글 내용
  private Date regDate; // 게시글 작성일
  private Date updateDate; // 게시글 수정일
  private String category; // 게시글 카테고리(0 : 커뮤니티, 1 : 플로깅)
  @Setter
  private Long ploggingNo;
  @Setter
  private Integer replyCount;

  /**
   * @author : 김성진
   * @date: 23.06.05
   * 
   * @param: -
   * @return: entity
   * 
   * @brief: DTO -> entity
   */
  public BoardEntity toEntity(BoardDTO boardDTO) {
    return BoardEntity.builder()
            .memberNo(MemberEntity.builder().memberNo(boardDTO.getMemberNo()).build())
            .title(boardDTO.getTitle())
            .content(boardDTO.content)
            .category(BoardCategory.valueOf(boardDTO.category))
            .regDate(new Date())
            .build();
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
  public BoardDTO(MemberEntity memberEntity, String title, String content, BoardCategory category) {
    this.memberNo = memberEntity.getMemberNo();
    this.title = title;
    this.content = content;
    this.category = category.getKey();
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
    public BoardDTO(BoardEntity boardEntity) {
      this.bno = boardEntity.getBno();
      this.memberNo = boardEntity.getMemberNo().getMemberNo();
      this.userId = boardEntity.getMemberNo().getUserId();
      this.title = boardEntity.getTitle();
      this.content = boardEntity.getContent();
      this.regDate = boardEntity.getRegDate();
      this.updateDate = boardEntity.getUpdateDate();
      this.category = boardEntity.getCategory().getKey();
      this.replyCount = boardEntity.getReplys().size();
  }
}
