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
public class ChallengeScheduleDTO {
    private Long scheduleNo;
    private Long chNo;
    private Date startDate;
    private Date endDate;
    private Date regDate;
    private Long mapNo;
}
