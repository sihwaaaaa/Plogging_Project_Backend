package city.olooe.plogging_project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeMemberDTO {
    private Long cmemberNo;
    private Long chNo;
    private Long challenger;
    private Date regDate;
}
