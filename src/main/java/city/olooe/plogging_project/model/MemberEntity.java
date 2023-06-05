package city.olooe.plogging_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_member", uniqueConstraints = { @UniqueConstraint(columnNames = "nickName") })
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private Long memberNo;
  private String userId;
  private String password;
  private String userName;
  private String email;
  private Date regDate;
  private String address;
  private String nickName;
  private String birth;
  private String gender;
  private Long totalPoint;
  private Long currentPoint;
  private String authProvider;

}
