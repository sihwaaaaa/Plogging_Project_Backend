package city.olooe.plogging_project.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.ChallengeScheduleEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.map.MapEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    private Date endDate;
    private Date regDate;
    private Long mapNo;


    public ChallengeScheduleDTO(final ChallengeScheduleEntity challengeScheduleEntity) {
        this.scheduleNo = challengeScheduleEntity.getScheduleNo();
        this.chNo = challengeScheduleEntity.getChNo().getChNo();
        this.startDate = challengeScheduleEntity.getStartDate();
        this.regDate = challengeScheduleEntity.getRegDate();
        this.mapNo = challengeScheduleEntity.getMapNo().getMapNo();
    }

    public static ChallengeScheduleEntity chToEntity(final ChallengeScheduleDTO challengeScheduleDTO) {
        return ChallengeScheduleEntity.builder()
                .scheduleNo(challengeScheduleDTO.scheduleNo)
                .chNo(ChallengeEntity.builder().chNo(challengeScheduleDTO.chNo).build())
                .startDate(challengeScheduleDTO.getStartDate())
                .regDate(challengeScheduleDTO.regDate)
                .mapNo(MapEntity.builder().mapNo(challengeScheduleDTO.mapNo).build())
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
    public ChallengeScheduleEntity toChallengeScheduleEntity() {
        return ChallengeScheduleEntity.builder().
                scheduleNo(scheduleNo).
                chNo(ChallengeEntity.builder().chNo(chNo).build())
                .startDate(startDate)
                .mapNo(MapEntity.builder().mapNo(mapNo).build())
                .build();
    }

    public ChallengeScheduleEntity toScheduleNo() {
        return  ChallengeScheduleEntity.builder().scheduleNo(ChallengeScheduleEntity.builder().scheduleNo(scheduleNo).build().getScheduleNo())
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
