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

  /**
   * @author: 박연재
   * @date: 2023.06.01
   * @brief: 회원가입용 dto 생성
   * @param entity
   */
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
