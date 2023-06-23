package city.olooe.plogging_project.model;

import java.lang.reflect.Member;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_challengemember")
public class ChallengeMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmemberNo; // 챌린지맴버 번호

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChallengeEntity.class)
    @JoinColumn(name = "chNo")
    private ChallengeEntity chNo; // 챌린지의 번호FK

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "memberNo")
    private MemberEntity challenger; // 챌린지원의 번호 FK

    private Date regDate; // 챌린지 가입날짜
}