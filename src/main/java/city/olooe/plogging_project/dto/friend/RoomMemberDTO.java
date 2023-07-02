package city.olooe.plogging_project.dto.friend;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import city.olooe.plogging_project.model.friend.RoomMemberEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class RoomMemberDTO {

    private Long rMemberNo;
    private Long roomNo;
    private String roomName;
    private Long memberNo;
    private String memberName;
    private String memberId;
    private String memberNickname;

    public RoomMemberDTO(RoomMemberEntity roomMemberEntity){
        this.rMemberNo = roomMemberEntity.getRmemberNo();
        this.roomNo = roomMemberEntity.getRoomNo().getRoomNo();
        this.roomName = roomMemberEntity.getRoomNo().getRoomName();
        this.memberNo = roomMemberEntity.getMemberNo().getMemberNo();
        this.memberName = roomMemberEntity.getMemberNo().getUserName();
        this.memberId = roomMemberEntity.getMemberNo().getUserId();
        this.memberNickname = roomMemberEntity.getMemberNo().getNickName();
    }

    public RoomMemberEntity toEntity(RoomMemberDTO memberDTO) {
        return RoomMemberEntity.builder()
                .rmemberNo(memberDTO.getRMemberNo())
                .roomNo(MessageRoomEntity.builder().roomNo(memberDTO.getRoomNo()).build())
                .memberNo(MemberEntity.builder().memberNo(memberDTO.getMemberNo()).build())
                .build();
    }

}
