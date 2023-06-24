package city.olooe.plogging_project.dto;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeMemberEntity;
import city.olooe.plogging_project.model.MemberEntity;
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
    private ChallengeDTO challenge;

    public ChallengeMemberDTO(final ChallengeMemberEntity challengeMemberEntity) {
        this.cmemberNo = challengeMemberEntity.getCmemberNo();
        this.chNo = challengeMemberEntity.getChNo().getChNo();
        this.challenger = challengeMemberEntity.getChallenger().getMemberNo();
        this.regDate = challengeMemberEntity.getRegDate();
        this.challenge = new ChallengeDTO(challengeMemberEntity.getChNo());
    }

    /*
     * @author : 김민수
     * 
     * @date: 23.06.09
     * 
     * @param: ChallengeDTO
     * 
     * @return: ChallengeEntity
     * 
     * @brief: ChallengeDTO를 ChallengeEntity 변환
     */
    public static ChallengeMemberEntity chToEntity(final ChallengeMemberDTO challengeMemberDTO) {
        return ChallengeMemberEntity.builder()
                .cmemberNo(challengeMemberDTO.getCmemberNo())
                .chNo(ChallengeEntity.builder().chNo(challengeMemberDTO.chNo).build())
                .challenger(MemberEntity.builder().memberNo(challengeMemberDTO.challenger).build())
                .regDate(challengeMemberDTO.regDate)
                .build();
    }

    /**
     * @author : 김민수
     * @date: 23.06.09
     * 
     * @param: -
     * @return: entity
     * 
     * @brief: DTO -> entity
     */
    public ChallengeMemberEntity toChallengeMemberEntity() {
        return ChallengeMemberEntity.builder().cmemberNo(cmemberNo).chNo(ChallengeEntity.builder().chNo(chNo).build())
                .challenger(MemberEntity.builder().memberNo(challenger).build()).regDate(regDate)
                .build();
    }

    public ChallengeMemberEntity toChNo() {
        return ChallengeMemberEntity.builder().chNo(ChallengeEntity.builder().chNo(chNo).build())
                .build();
    }

    /**
     * @author : 김민수
     * @date: 23.06.16
     *
     * @param: -
     * @return: entity
     *
     * @brief: DTO -> entity
     */
    public ChallengeMemberEntity toChMemberDelete() {
        return ChallengeMemberEntity.builder().cmemberNo(cmemberNo)
                .chNo(ChallengeEntity.builder().chNo(chNo).build())
                .challenger(MemberEntity.builder().memberNo(challenger).build())
                .build();
    }

}
