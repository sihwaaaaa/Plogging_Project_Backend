package city.olooe.plogging_project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class AuthEntity implements Serializable{

  @Id
  @JoinColumn(name = "memberNo")
  private Long no; // 권한 번호

  @Id
  private String authority; // 권한 타입

  @ManyToOne(fetch = FetchType.LAZY)
  private MemberEntity memberEntity;
}
