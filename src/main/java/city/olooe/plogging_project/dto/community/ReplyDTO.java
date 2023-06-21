package city.olooe.plogging_project.dto.community;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import city.olooe.plogging_project.model.community.ReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 댓글 DTO
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@DynamicInsert
@DynamicUpdate
public class ReplyDTO {
    private Long rno;
    private String reply;
    private Long replyerNo;
    private String replyerId;
    private Date regDate;
    private Long bno;

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyEntity
     * @Brief entity -> dto 변환
     */
    public ReplyDTO(ReplyEntity replyEntity){
        this.rno = replyEntity.getRno();
        this.reply = replyEntity.getReply();
        this.replyerNo = replyEntity.getReplyer().getMemberNo();
        this.replyerId = replyEntity.getReplyer().getUserId();
        this.regDate = replyEntity.getRegDate();
        this.bno = replyEntity.getBno().getBno();
    }

    /**
     * @Author 천은경
     * @Date 23.06.20
     * @param replyDTO
     * @Brief dto -> entity -> 변환
     */
    public static ReplyEntity toEntity(final ReplyDTO replyDTO) {
        return ReplyEntity.builder()
                .reply(replyDTO.reply)
                .replyer(MemberEntity.builder().memberNo(replyDTO.replyerNo).build())
                .bno(BoardEntity.builder().bno(replyDTO.bno).build())
                .build();
    }

}
