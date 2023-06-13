package city.olooe.plogging_project.dto;

import java.time.LocalDate;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate regDate;
    private Long mapNo;
}
