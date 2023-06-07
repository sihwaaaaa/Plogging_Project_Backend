package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.FriendEntity;
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
    public FriendDTO(FriendEntity friendEntity) {
        fromMemberNo = friendEntity.getFromMember().getMemberNo();
        toMemberNo = friendEntity.getToMember().getMemberNo();
        status = friendEntity.getStatus().getKey();
    }
}
