package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PointHistoryDTO {
    private Long pointNo; // 포인트 번호 PK
    private MemberEntity memberNo; // 멤버 번호 FK
    private String type; // 유형
    private Long point; // 포인트
    private RewardEntity rewardNo; // 리워드 번호 FK

    public PointHistoryDTO(final PointHistoryEntity historyEntity) {
        this.pointNo = getPointNo();
        this.memberNo = getMemberNo();
        this.rewardNo = getRewardNo();
        this.point = getPoint();
        this.type = getType();
    }
    public static PointHistoryDTO testEntity(PointHistoryEntity entity) {
        PointHistoryDTO dto = new PointHistoryDTO();
        dto.setMemberNo(dto.getMemberNo());
        dto.setType(dto.getType());
        dto.setPoint(dto.getPoint());
        return dto;
    }
    public static PointHistoryEntity toEntity(final PointHistoryDTO historyDTO) {
        return PointHistoryEntity.builder()
                .pointNo(historyDTO.getPointNo())
                .memberNo(historyDTO.getMemberNo())
                .rewardNo(historyDTO.getRewardNo())
                .type(RewardTypeStatus.valueOf(historyDTO.type))
                .point(historyDTO.getPoint())
                .build();
    }

    /**
     * @author : 이재원
     * @date: 23.06.12 *
     * @param: -
     * @return: entity *
     * @brief: DTO -> entity
     */
    public PointHistoryEntity changeEntity() {
        return PointHistoryEntity.builder()
                .pointNo(pointNo)
                .point(point)
                .memberNo(memberNo)
                .rewardNo(rewardNo)
                .type(RewardTypeStatus.valueOf(getType()))
                .build();
    }
    public void TypeByValuePoint() {
        this.point = RewardTypeStatus.valueOf(type).getValue();
    }
    public void TradePoint(Long value) {
        this.point += value;
    }
}