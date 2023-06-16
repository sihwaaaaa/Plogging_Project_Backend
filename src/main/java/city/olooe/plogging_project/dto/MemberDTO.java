package city.olooe.plogging_project.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import city.olooe.plogging_project.model.AuthEntity;
import city.olooe.plogging_project.model.AuthType;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.*;

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

  private String token; // 토큰 문자열

  private Long memberNo; // 회원 번호
  private String userId; // 회원 아이디
  private String password; // 회원 비밀번호
  private String userName; // 회원 닉네임
  private String email; // 이메일
  private Date regDate; // 등록일자
  private String address; // 주소
  private String addressDetail; // 세부 주소
  private String nickName; // 닉네임
  private Date birth; // 생년월일
  private String gender; // 성별
  private String authProvider; // 권한 공급자
  private String intro;
  private List<String> authList; // 권한

  /**
   * @author: 박연재
   * @date: 2023.06.01
   * @brief: 회원가입용 dto 생성
   * @param entity
   */
  // public MemberDTO(final MemberEntity entity) {

  // MemberDTO.builder()
  // .memberNo(entity.getMemberNo())
  // .userId(entity.getUserId())
  // .password(entity.getPassword())
  // .userName(entity.getUserName())
  // .email(entity.getEmail())
  // .regDate(entity.getRegDate())
  // .address(entity.getAddress())
  // .addressDetail(entity.getAddressDetail())
  // .nickName(entity.getNickName())
  // .birth(entity.getBirth())
  // .gender(entity.getGender())
  // .authProvider(entity.getAuthProvider())
  // .build();
  // }

  public MemberDTO(final MemberEntity memberEntity) {
    this.memberNo = memberEntity.getMemberNo();
    this.userId = memberEntity.getUserId();
    this.password = memberEntity.getPassword();
    this.userName = memberEntity.getUserName();
    this.email = memberEntity.getEmail();
    this.regDate = memberEntity.getRegDate();
    this.address = memberEntity.getAddress();
    this.addressDetail = memberEntity.getAddressDetail();
    this.nickName = memberEntity.getNickName();
    this.birth = memberEntity.getBirth();
    this.gender = memberEntity.getGender();
    this.authProvider = memberEntity.getAuthProvider();
  }

  /**
   * @author: 박연재
   * @date: 2023.06.07
   * @brief: adminDTO 메서드 기능
   * @param entity
   * @return MemberDTO
   */
  public MemberDTO adminDTO(final MemberEntity entity) {
    MemberDTO admin = memberDTO(entity);
    admin.setMemberNo(entity.getMemberNo());
    return admin;
  }

  /**
   * @author: 박연재
   * @date: 2023.06.07
   * @brief: memberDTO 메서드 기능
   * @param entity
   * @return MemberDTO
   */
  public MemberDTO memberDTO(final MemberEntity entity) {
    return MemberDTO.builder()
        .userId(entity.getUserId())
        .password(entity.getPassword())
        .userName(entity.getUserName())
        .email(entity.getEmail())
        .regDate(entity.getRegDate())
        .address(entity.getAddress())
        .addressDetail(entity.getAddressDetail())
        .nickName(entity.getNickName())
        .birth(entity.getBirth())
        .gender(entity.getGender())
        .authProvider(entity.getAuthProvider())
        .build();
  }

  public static MemberEntity memberDtoToEntity(final MemberDTO dto) {
    return MemberEntity.builder()
        .userId(dto.getUserId())
        .password(dto.getPassword())
        .userName(dto.getUserName())
        .email(dto.getEmail())
        .regDate(dto.getRegDate())
        .address(dto.getAddress())
        .addressDetail(dto.getAddressDetail())
        .nickName(dto.getNickName())
        .birth(dto.getBirth())
        .gender(dto.getGender())
        .authProvider(dto.getAuthProvider())
        .build();
  }
}
