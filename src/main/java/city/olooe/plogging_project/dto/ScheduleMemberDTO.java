package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.model.map.MapEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleMemberDTO {

    private Long smno;
    private Long challenger;
    private Long scheduleNo;
    private Long chNo;

    private List<Long> scheduleMembers;
    private Integer challengeMemberCnt;

    public ScheduleMemberDTO(final SchedulMemberEntity schedulMemberEntity) {
        this.smno = schedulMemberEntity.getSmno();
        this.challenger = schedulMemberEntity.getChallenger().getMemberNo();
        this.scheduleNo = schedulMemberEntity.getScheduleNo().getScheduleNo();
        this.chNo = schedulMemberEntity.getChNo().getChNo();
        this.challengeMemberCnt = schedulMemberEntity.getSchMembersCnt().size();
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
    public SchedulMemberEntity toschedulMemberEntity() {
        return SchedulMemberEntity.builder().challenger(MemberEntity.builder().memberNo(challenger).build())
                .scheduleNo(ChallengeScheduleEntity.builder().scheduleNo(scheduleNo).build())
                .chNo(ChallengeEntity.builder().chNo(chNo).build())
                .build();
    }


    public  SchedulMemberEntity schedeulCancle(){
        return SchedulMemberEntity.builder().smno(smno)
                .build();
    }

}
