package city.olooe.plogging_project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_challenge")
@DynamicInsert
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chNo; // 챌린지 번호 PK

    private Boolean blind; // 공개,비공개 여부

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "memberNo")
    @Setter
    private MemberEntity host; // 챌린지 리더

    private String title; // 챌린지 제목
    private String content; // 챌린지 소개
    private Long personnel; // 챌린지원 수
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regDate; // 만든날짜
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate; // 시작날짜
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate; // 끝나는 날짜

    @Enumerated(EnumType.STRING)
    @Setter
    private ChallengeStatus status; // 챌린지 진행전 / 진행중 / 마감 / 인원마감 / 챌린지 종료

    @OneToMany(mappedBy = "chNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChallengeMemberEntity> ChallengeMembers = new ArrayList<>();
}
