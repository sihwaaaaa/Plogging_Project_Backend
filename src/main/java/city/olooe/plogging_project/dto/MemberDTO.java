package city.olooe.plogging_project.dto;

import java.util.Date;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 박연재
 * @date: 23.06.01
 * @brief: MemberDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {


  private String token;

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

  public MemberDTO(final MemberEntity entity) {
    this.memberNo = entity.getMemberNo();
    this.userId = entity.getUserId();
    this.password = entity.getPassword();
    this.userName = entity.getUserName();
    this.email = entity.getEmail();
    this.regDate = entity.getRegDate();
    this.address = entity.getAddress();
    this.nickName = entity.getNickName();
    this.birth = entity.getBirth();
    this.gender = entity.getGender();
    this.totalPoint = entity.getTotalPoint();
    this.currentPoint = entity.getCurrentPoint();
    this.authProvider = entity.getAuthProvider();
  }

  public static MemberEntity toEntity(final MemberDTO dto) {
    return MemberEntity.builder()
        .memberNo(dto.getMemberNo())
        .userId(dto.getUserId())
        .password(dto.getPassword())
        .userName(dto.getUserName())
        .email(dto.getEmail())
        .regDate(dto.getRegDate())
        .address(dto.getAddress())
        .nickName(dto.getNickName())
        .birth(dto.getBirth())
        .gender(dto.getGender())
        .totalPoint(dto.getTotalPoint())
        .currentPoint(dto.getCurrentPoint())
        .authProvider(dto.getAuthProvider())
        .build();
  }
}
