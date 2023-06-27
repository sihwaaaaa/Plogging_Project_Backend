package city.olooe.plogging_project.model;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_schedulemember")
public class SchedulMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smno; // 챌린지일정 맴버리스트의 pk

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "memberNo")
    private MemberEntity challenger; // 챌린지원의 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeScheduleEntity.class)
    @JoinColumn(name = "scheduleNo")
    private ChallengeScheduleEntity scheduleNo; // 챌린지일정 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeEntity.class)
    @JoinColumn(name = "chNo")
    private ChallengeEntity chNo;

    @OneToMany(mappedBy = "scheduleNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChallengeScheduleEntity> schMembersCnt = new ArrayList<>();

    public SchedulMemberEntity(Long scheduleNo) {
        this.scheduleNo = ChallengeScheduleEntity.builder().scheduleNo(scheduleNo).build();
    }


}
