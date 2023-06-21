package city.olooe.plogging_project.model.community;

import city.olooe.plogging_project.model.MemberEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author 천은경
 * @Date 23.06.20
 * @Brief 댓글 Entity
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@Table(name="tbl_reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    private String reply;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "replyer", referencedColumnName = "memberNo")
    private MemberEntity replyer;
    @JsonFormat
    private Date regDate;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardEntity.class)
    @JoinColumn(name = "bno", referencedColumnName = "bno")
    private BoardEntity bno;


}
