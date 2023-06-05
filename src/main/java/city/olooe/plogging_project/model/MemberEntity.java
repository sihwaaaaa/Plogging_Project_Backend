package city.olooe.plogging_project.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.ibatis.annotations.One;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
  private String password; // 회원 비밀번호
  private String userName; // 회원 이름
  private String email; // 회원 이메일
  private Date regDate; // 회원 등록일자
  private String address; // 회원 주소
  private String nickName; // 회원 닉네임
  private String birth; // 회원 생년월일
  private String gender; // 회원 성별
  private Long totalPoint; // 회원 누적 포인트
  private Long currentPoint; // 회원 현재 포인트
  private String authProvider; // 회원 oauth 2.0 로그인 공급자

  public MemberEntity(String userId, String password, String userName, String email) {
    this.userId = userId;
    this.password = password;
    this.userName = userName;
    this.email = email;
  }

  @OneToMany(mappedBy = "memberEntity")
  private List<AuthEntity> authEntities = new ArrayList<>();

  // @OneToMany(mappedBy = "host")
  // private List<ChallengeEntity> challengeEntities = new ArrayList<>();

  @OneToMany(mappedBy = "memberEntity")
  private List<RewardEntity> rewardEntities = new ArrayList<>();
}
