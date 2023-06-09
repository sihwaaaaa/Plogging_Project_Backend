package city.olooe.plogging_project.model;

import javax.persistence.Entity;
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
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_pointhistory")
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointNo; // PK

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "rewardNo")
    private RewardEntity rewardEntity;

    private String type;
    private Long point;

}
