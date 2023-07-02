package city.olooe.plogging_project.dto.friend;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class MessageRoomDTO {
    private Long roomNo;
    private String roomName;
    @Setter
    private List<MessageDTO> messageList = new ArrayList<>();
    @Setter
    private List<RoomMemberDTO> rMemberList = new ArrayList<>();

    public MessageRoomDTO(MessageRoomEntity messageRoomEntity){
        this.roomNo = messageRoomEntity.getRoomNo();
        this.roomName = messageRoomEntity.getRoomName();
        this.messageList = messageRoomEntity.getMessageList() != null ? messageRoomEntity.getMessageList().stream().map(MessageDTO::new).collect(Collectors.toList()) : null;
        this.rMemberList = messageRoomEntity.getRoomMembers() != null ? messageRoomEntity.getRoomMembers().stream().map(RoomMemberDTO::new).collect(Collectors.toList()) : null;
    }

    public MessageRoomEntity toEntity(MessageRoomDTO messageRoomDTO) {
        return MessageRoomEntity.builder()
                .roomNo(messageRoomDTO.getRoomNo())
                .roomName(messageRoomDTO.getRoomName())
                .build();
    }

}
