package city.olooe.plogging_project.model;

import city.olooe.plogging_project.model.BadgeEntity;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@Table(name = "tbl_attaches")
public class AttachEntity {

    @Id
    private String uuid;
    private String path;
    private String filename;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardEntity.class)
    @JoinColumn(name = "bno", referencedColumnName = "bno")
    private BoardEntity bno;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeEntity.class)
    @JoinColumn(name = "chNo", referencedColumnName = "chNo")
    private ChallengeEntity chNo;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BadgeEntity.class)
    @JoinColumn(name = "badgeNo", referencedColumnName = "badgeNo")
    private BadgeEntity badgeNo;

}
