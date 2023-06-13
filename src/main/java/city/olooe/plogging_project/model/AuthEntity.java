package city.olooe.plogging_project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author - 박연재
 * @date - 2023.06.01
 * @brief - 권한 테이블
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_auth")
public class AuthEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authNo;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
  @JoinColumn(name = "memberNo", referencedColumnName = "memberNo")
  @Setter
  private MemberEntity memberNo; // 멤버 PK

  @Enumerated(EnumType.STRING)
  private AuthType authority; // 권한 타입

  public AuthEntity(final MemberEntity memberEntity, final AuthType authType) {
    this.memberNo = memberEntity;
    this.authority = authType;
  }

}
