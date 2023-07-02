package city.olooe.plogging_project.model.friend;

import city.olooe.plogging_project.model.MemberEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long msgNo;
    private Date sendTime;
    private Long chk;
    private String content;
    @ManyToOne(targetEntity = MessageRoomEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "roomNo", name = "roomNo")
    private MessageRoomEntity roomNo;
    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", referencedColumnName = "memberNo")
    private MemberEntity sender;

    public void checkMessage() {
        this.chk++;
    }

}
