package city.olooe.plogging_project.model;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_pointhistory")
@DynamicInsert
@Getter
public class PointHistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointNo; // PK

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "memberNo")
    private MemberEntity memberNo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RewardEntity.class)
    @JoinColumn(name = "rewardNo")
    private RewardEntity rewardNo;

    @Enumerated(EnumType.STRING)
    @Setter
    private RewardTypeStatus type;
    private boolean status; // default 0(false)
    private Long point;
    private Date regDate;
}
