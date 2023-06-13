package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PointHistoryDTO {
    private Long pointNo; // 포인트 번호 PK
    private Long memberNo; // 멤버 번호 FK
    private String type; // 유형
    private Long point; // 포인트
    private Long rewardNo; // 리워드 번호 FK


    public PointHistoryDTO(final PointHistoryEntity historyEntity) {
        this.pointNo = historyEntity.getPointNo();
        this.memberNo = historyEntity.getMemberEntity().getMemberNo();
        this.rewardNo = historyEntity.getRewardEntity().getRewardNo();
        this.type = historyEntity.getType().getKey();
        this.point = historyEntity.getPoint();
    }

    public static PointHistoryEntity toEntity(final PointHistoryDTO historyDTO) {
        return PointHistoryEntity.builder()
                .pointNo(historyDTO.getPointNo())
                .memberEntity(MemberEntity.builder().memberNo(historyDTO.getMemberNo()).build())
                .rewardEntity(RewardEntity.builder().rewardNo(historyDTO.getRewardNo()).build())
                .type(RewardTypeStatus.valueOf(historyDTO.type))
                .point(historyDTO.getPoint())
                .build();
    }
}
