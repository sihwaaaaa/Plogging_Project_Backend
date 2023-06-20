package city.olooe.plogging_project.dto;

import java.time.LocalDate;
import java.util.Date;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.ChallengeScheduleEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.MapEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeScheduleDTO {
    private Long scheduleNo;
    private Long chNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate regDate;
    private Long mapNo;

    /**
     * @author : 김민수
     * @date: 23.06.016
     *
     * @param: -
     * @return: entity
     *
     * @brief: DTO -> entity
     */
    public ChallengeScheduleEntity toChallengeScheduleEntity() {
        return ChallengeScheduleEntity.builder().scheduleNo(scheduleNo).chNo(ChallengeEntity.builder().chNo(chNo).build())
                .startDate(startDate).endDate(endDate).mapNo(MapEntity.builder().mapNo(mapNo).build())
                .build();
    }

    /**
     * @author : 김민수
     * @date: 23.06.016
     *
     * @param: -
     * @return: entity
     *
     * @brief: DTO -> entity
     */
    public ChallengeScheduleEntity toChallengeDelete() {
        return ChallengeScheduleEntity.builder().scheduleNo(scheduleNo)
                .chNo(ChallengeEntity.builder().chNo(chNo).build())
                .build();
    }
}
