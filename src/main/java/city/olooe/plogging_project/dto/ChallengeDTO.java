package city.olooe.plogging_project.dto;

import java.util.Date;

import javax.swing.text.AbstractDocument.Content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeDTO {
    private Long chNo;
    private Long blind;
    private Long host;
    private String title;
    private String content;
    private Long personnel;
    private Date regDate;
    private Date startDate;
    private Date endDate;
}
