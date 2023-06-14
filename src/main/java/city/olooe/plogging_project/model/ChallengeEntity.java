package city.olooe.plogging_project.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chNo; // 챌린지 번호 PK

    private Boolean blind; // 공개,비공개 여부

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class,
    cascade = CascadeType.PERSIST)
    @JoinColumn(name = "memberNo")
    private MemberEntity host; // 챌린지 리더

    private String title; // 챌린지 제목
    private String content; // 챌린지 소개
    private Long personnel; // 챌린지원 수
    private Date regDate; // 만든날짜
    private Date startDate; // 시작날짜
    private Date endDate; // 끝나는 날짜

    @Enumerated(EnumType.STRING)
    @Setter
    private ChallengeStatus status; // 챌린지 진행전 / 진행중 / 마감 / 인원마감 / 챌린지 종료
}
