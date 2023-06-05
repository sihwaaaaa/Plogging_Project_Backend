package city.olooe.plogging_project.dto;

import java.util.Date;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeDTO {
    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @brief: ChallengDTO
     */
    private Long chNo;
    private Integer blind;
    private MemberEntity host;
    private String title;
    private String content;
    private Long personnel;
    private Date regDate;
    private Date startDate;
    private Date endDate;

    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @param: ChallengeEntity
     * 
     * @return: void
     * 
     * @brief: Entity를 DTO로 변환
     */
    public ChallengeDTO(final ChallengeEntity chEntity) {
        this.chNo = chEntity.getChNo();
        this.blind = chEntity.getBlind();
        this.host = chEntity.getHost();
        this.title = chEntity.getContent();
        this.content = chEntity.getContent();
        this.personnel = chEntity.getPersonnel();
        this.regDate = chEntity.getRegDate();
        this.startDate = chEntity.getStartDate();
        this.endDate = chEntity.getEndDate();
    }

    /*
     * @author : 김민수
     * 
     * @date: 23.06.02
     * 
     * @param: MemberDTO
     * 
     * @return: MapEntity
     * 
     * @brief: MemberDTO를 MapEntity로 변환
     */
    public static ChallengeEntity chToEntity(final ChallengeDTO challengeDTO) {
        return ChallengeEntity.builder()
                .chNo(challengeDTO.getChNo())
                .blind(challengeDTO.getBlind())
                .host(challengeDTO.getHost())
                .title(challengeDTO.getTitle())
                .content(challengeDTO.getContent())
                .personnel(challengeDTO.getPersonnel())
                .regDate(challengeDTO.getRegDate())
                .startDate(challengeDTO.getStartDate())
                .endDate(challengeDTO.endDate)
                .build();
    }

}