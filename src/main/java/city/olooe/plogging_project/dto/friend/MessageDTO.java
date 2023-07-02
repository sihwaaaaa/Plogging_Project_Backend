package city.olooe.plogging_project.dto.friend;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.MessageEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class MessageDTO {
    private Long msgNo;
    private Date sendTime;
    private Long chk;
    private String content;
    private Long roomNo;
    private String roomName;
    private Long senderNo;
    private String senderId;
    private String senderName;
    private String senderNickName;

    public MessageDTO(MessageEntity messageEntity){
        this.msgNo = messageEntity.getMsgNo();
        this.sendTime = messageEntity.getSendTime();
        this.chk = messageEntity.getChk();
        this.content = messageEntity.getContent();
        this.roomNo = messageEntity.getRoomNo().getRoomNo();
        this.roomName = messageEntity.getRoomNo().getRoomName();
        this.senderNo = messageEntity.getSender().getMemberNo();
        this.senderId = messageEntity.getSender().getUserId();
        this.senderName = messageEntity.getSender().getUserName();
        this.senderNickName = messageEntity.getSender().getNickName();
    }

    public MessageEntity toEntity() {
        return MessageEntity.builder()
                .msgNo(msgNo)
                .sendTime(sendTime)
                .chk(chk)
                .content(content)
                .roomNo(MessageRoomEntity.builder().roomNo(roomNo).build())
                .sender(MemberEntity.builder().memberNo(senderNo).build())
                .build();
    }

}
