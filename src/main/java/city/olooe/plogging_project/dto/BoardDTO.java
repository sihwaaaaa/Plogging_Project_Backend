package city.olooe.plogging_project.dto;

import java.time.LocalDateTime;

import city.olooe.plogging_project.model.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  private Long bno;
  private String title;
  private String content;
  private LocalDateTime regDate;
  private LocalDateTime updateDate;

  private Long writer;

  public BoardEntity boardEntity() {
    BoardEntity boardBuild = BoardEntity.builder().bno(bno).writer(writer).title(title).content(content).build();

    return boardBuild;
  }

  @Builder
  public BoardDTO(Long bno, Long writer, String title, String content, LocalDateTime regDate,
      LocalDateTime updateDate) {
    this.bno = bno;
    this.writer = writer;
    this.title = title;
    this.content = content;
    this.regDate = regDate;
    this.updateDate = updateDate;
  }

}
