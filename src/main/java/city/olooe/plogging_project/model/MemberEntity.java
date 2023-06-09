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
import lombok.Setter;
import lombok.ToString;

/**
 * @author: �ڿ���
 * @date: 2023.06.01
 * @brief: ��� ��ƼƼ
 */
@DynamicInsert
@ToString
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_member", uniqueConstraints = { @UniqueConstraint(columnNames = "nickName") })
public class MemberEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberNo; // ȸ�� ��ȣ
  private String userId; // ȸ�� ���̵�
  private String password; // ȸ�� ��й�ȣ
  private String userName; // ȸ�� �̸�
  private String email; // ȸ�� �̸���
  private Date regDate; // ȸ�� �������
  private String address; // ȸ�� �ּ�
  private String nickName; // ȸ�� �г���
  private String birth; // ȸ�� �������
  private String gender; // ȸ�� ����
  private String authProvider; // ȸ�� oauth 2.0 �α��� ������

  public MemberEntity(String userId, String password, String userName, String email) {
    this.userId = userId;
    this.password = password;
    this.userName = userName;
    this.email = email;
  }

  // public void updateCurr(Long memberNo, Long currentPoint) {
  // this.memberNo = memberNo;
  // this.currentPoint = currentPoint;
  // }

  // @OneToMany(mappedBy = "memberEntity")
  // private List<AuthEntity> authEntities = new ArrayList<>();
  //
  // // @OneToMany(mappedBy = "host")
  // // private List<ChallengeEntity> challengeEntities = new ArrayList<>();
  //
  // @OneToMany(mappedBy = "memberEntity")
  // private List<RewardEntity> rewardEntities = new ArrayList<>();
}