package city.olooe.plogging_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleMemberDTO {

    private Long smno;
    private Long cmemberNo;
    private Long scheduleNo;
}
