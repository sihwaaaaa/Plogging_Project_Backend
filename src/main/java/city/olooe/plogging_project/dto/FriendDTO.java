package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.FriendEntity;
import city.olooe.plogging_project.model.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author : 천은경
 * @date: 23.06.02
 * @brief: 플친 DTO
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FriendDTO {
    private Long fromMemberNo;
    private Long toMemberNo;
    private String status;

    // Entity를 Dto로 변환
    public FriendDTO(final FriendEntity friendEntity) {
        fromMemberNo = friendEntity.getFromMember().getMemberNo();
        toMemberNo = friendEntity.getToMember().getMemberNo();
        status = friendEntity.getStatus().getKey();
    }

    // DTO를 Entity로 변환
    public static FriendEntity toEntity(final FriendDTO friendDTO) {
        return FriendEntity.builder()
                .fromMember(MemberEntity.builder().memberNo(friendDTO.fromMemberNo).build())
                .toMember(MemberEntity.builder().memberNo(friendDTO.toMemberNo).build())
                .status(FriendStatusType.valueOf(friendDTO.status)).build();
    }
}
