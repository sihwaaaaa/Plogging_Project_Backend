package city.olooe.plogging_project.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.*;

import city.olooe.plogging_project.model.community.BoardEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import city.olooe.plogging_project.model.map.PloggingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: 박연재
 * @date: 2023.06.01
 * @brief: 멤버 엔티티
 */
@DynamicInsert
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_member", uniqueConstraints = { @UniqueConstraint(columnNames = "nickName") })
public class MemberEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberNo; // 회원 번호
  private String userId; // 회원 아이디
  private String password; // 비밀번호
  private String userName; // 이름
  private String email; // 이메일
  private Date regDate; // 등록일자
  private String address; // 주소
  private String addressDetail; // 세부 주소
  private String nickName; // 닉네임
  private Date birth; // 생년월일
  private String gender; // 성별
  private String authProvider; // oauth 2.0 연동 공급자
  private String intro;

  public MemberEntity(String userId, String password, String userName, String email) {
    this.userId = userId;
    this.password = password;
    this.userName = userName;
    this.email = email;
  }

  @OneToMany(mappedBy = "memberNo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<AuthEntity> authEntities = new ArrayList<>();

  public void setAuthEntities(List<AuthEntity> authEntities) {
    this.authEntities = authEntities;
    if (this.authEntities != null && this.authEntities.size() > 0) {
      authEntities.forEach(authEntity -> authEntity.setMemberNo(this));
    }
  }

  @OneToOne(mappedBy = "challenger")
  private ChallengeMemberEntity challengeMemberEntity;

  @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
  private List<ChallengeEntity> myChallengesDetail = new ArrayList<>();

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<PloggingEntity> ploggingEntities = new ArrayList<>();

  @OneToMany(mappedBy = "memberNo", fetch = FetchType.LAZY)
  private List<BoardEntity> boardEntities = new ArrayList<>();

  @OneToMany(mappedBy = "memberNo", fetch = FetchType.LAZY)
  private List<PointHistoryEntity> pointHistoryEntities = new ArrayList<>();
  // @OneToMany(mappedBy = "chNo")
  // private List<PloggingEntity> ploggingEntities = new ArrayList<>();

}