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

<<<<<<< HEAD
  private String token; // 토큰 문자열

  private Long memberNo; // 회원 번호
  private String userId; // 회원 아이디
  private String password; // 회원 비밀번호
  private String userName; // 회원 닉네임
  private String email; // 이메일
  private Date regDate; // 등록일자
  private String address; // 주소
  private String nickName; // 닉네임
  private String birth; // 생년월일
  private String gender; // 성별
  private Long totalPoint; // 누적 포인트
  private Long currentPoint; // 현재 포인트
  private String authProvider; // 권한 공급자
=======
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
>>>>>>> origin/feature/reward

  /**
   * @author: 박연재
   * @date: 2023.06.01
   * @brief: 회원가입용 dto 생성
   * @param entity
   */
  public MemberDTO(final MemberEntity entity) {
<<<<<<< HEAD
    MemberDTO.builder()
        .memberNo(entity.getMemberNo())
        .userId(entity.getUserId())
        .password(entity.getPassword())
        .userName(entity.getUserName())
        .email(entity.getEmail())
        .regDate(entity.getRegDate())
        .address(entity.getAddress())
        .nickName(entity.getNickName())
        .birth(entity.getBirth())
        .gender(entity.getGender())
        .authProvider(entity.getAuthProvider())
        .build();
=======
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
    this.authProvider = entity.getAuthProvider();
>>>>>>> origin/feature/reward
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
        .nickName(dto.getNickName())
        .birth(dto.getBirth())
        .gender(dto.getGender())
        .authProvider(dto.getAuthProvider())
        .build();
  }
}
