package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.AuthEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDTO {

  private Long no; // 권한 번호
  private String authority; // 권한 타입

  private MemberEntity member;

  public AuthDTO(final AuthEntity entity) {
    this.no = entity.getNo();
    this.authority = entity.getAuthority();
    this.member = MemberEntity.builder()
        .memberNo(entity.getNo()).build();
  }

  public static AuthEntity toEntity(final AuthDTO authDTO) {
    return AuthEntity.builder()
        .no(authDTO.getNo())
        .authority(authDTO.getAuthority())
        .build();
  }
}
