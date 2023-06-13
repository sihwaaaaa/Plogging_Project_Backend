package city.olooe.plogging_project.model;

import javax.persistence.*;

import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Setter
    private RewardTypeStatus type;
    private Long point;

}
