package city.olooe.plogging_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailCheckDTO {

  private Long memberNo;
  private String userId;
  private String userName;
  private String email;
}
