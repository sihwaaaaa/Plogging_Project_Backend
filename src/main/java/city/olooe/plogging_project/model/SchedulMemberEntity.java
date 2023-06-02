package city.olooe.plogging_project.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_schedulemember")
public class SchedulMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smno; // 챌린지일정 맴버 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeMemberEntity.class)
    @JoinColumn(name = "cmemberNo")
    private ChallengeMemberEntity cmemberNo; // 챌린지원의 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeScheduleEntity.class)
    @JoinColumn(name = "scheduleNo")
    private ChallengeScheduleEntity scheduleNo; // 챌린지일정 번호

}
