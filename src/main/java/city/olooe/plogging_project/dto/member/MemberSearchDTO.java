package city.olooe.plogging_project.dto.member;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.map.PloggingDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.*;

import java.util.List;

/**
 * @author 천은경
 * @Date 23.06.16
 * @Brief 검색용 회원 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberSearchDTO {
    private Long memberNo;
    private String userId;
    private String userName;
//    @JsonFormat 추후 Date로 변경 필요
    private String intro;
    @Setter
    private List<String> challenges; // 가입한 챌린지명 목록

    @Setter
    private String friendStatus; // 플친 상태

    /**
     * @Author 천은경
     * @Date 23.06.15
     * @param entity
     * @Brief entity에서 dto 변환
     */
    public MemberSearchDTO(MemberEntity entity) {
        this.memberNo = entity.getMemberNo();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
    }

    /**
     * @Author 천은경
     * @Date 23.06.15
     * @param dto
     * @return MemberEntity
     * @Brief dto에서 entity 변환
     */
    public MemberEntity toEntity(MemberDTO dto) {
        return MemberEntity.builder()
                .memberNo(dto.getMemberNo())
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .build();
    }
}
