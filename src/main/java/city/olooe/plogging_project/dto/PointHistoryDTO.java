package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class PointHistoryDTO {
    private Long pointNo; // 포인트 번호 PK
    private Long memberNo; // 멤버 번호 FK
    private String type; // 유형
    private Long point; // 포인트
    private Long rewardNo; // 리워드 번호 FK
    private Date regDate; // 등록 일자
    private boolean status; // 상태값(defalt : 0)

    public PointHistoryDTO(final PointHistoryEntity historyEntity) {
        this.pointNo = historyEntity.getPointNo();
        this.memberNo = historyEntity.getMemberNo().getMemberNo();
        this.rewardNo = historyEntity.getRewardNo().getRewardNo();
        this.point = historyEntity.getPoint();
        this.type = historyEntity.getType().getKey();
        this.regDate = historyEntity.getRegDate();
        this.status = historyEntity.isStatus();
    }
    public static PointHistoryEntity toEntity(final PointHistoryDTO historyDTO) {
        return PointHistoryEntity.builder()
                .pointNo(historyDTO.getPointNo())
                .memberNo(MemberEntity.builder().memberNo(historyDTO.memberNo).build())
                .rewardNo(RewardEntity.builder().rewardNo(historyDTO.rewardNo).build())
                .regDate(historyDTO.getRegDate())
                .type(RewardTypeStatus.valueOf(historyDTO.type))
                .point(historyDTO.getPoint())
                .status(historyDTO.isStatus())
                .build();
    }

    /**
     * @author : 이재원
     * @date: 23.06.12 *
     * @param: -
     * @return: entity *
     * @brief: DTO -> entity
     */
//    public PointHistoryEntity changeEntity() {
//        return PointHistoryEntity.builder()
//                .pointNo(pointNo)
//                .point(point)
//                .memberNo(memberNo)
//                .rewardNo(rewardNo)
//                .regDate(regDate)
//                .status(isStatus())
//                .type(RewardTypeStatus.valueOf(getType()))
//                .build();
//    }
}