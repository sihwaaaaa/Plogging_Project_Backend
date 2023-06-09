package city.olooe.plogging_project.dto.map;

import city.olooe.plogging_project.model.map.UserMapEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이시화
 * @date: 23.06.02
 * 
 * @brief: UserMapDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMapDTO {
  private Long userMapNo;// pk
  private Long mapNo;// 지도번호 fk
  private Long memberNo;// 회원번호 fk

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: UserMapEntity
   * @return: void
   * 
   * @brief: entity -> DTO
   */
  public UserMapDTO(final UserMapEntity entity) {
    this.userMapNo = entity.getUserMapNo();
    this.mapNo = entity.getMapNo();
    this.memberNo = entity.getMemberNo();
  }

  /**
   * @author : 이시화
   * @date: 23.06.02
   * 
   * @param: UserMapDTO
   * @return: UserMapEntity
   * 
   * @brief: DTO -> Entity
   */
  public static UserMapEntity toEntity(final UserMapDTO userMapDTO) {
    return UserMapEntity.builder()
        .userMapNo(userMapDTO.getUserMapNo())
        .mapNo(userMapDTO.getMapNo())
        .memberNo(userMapDTO.getMemberNo())
        .build();
  }
}
