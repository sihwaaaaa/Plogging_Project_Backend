package city.olooe.plogging_project.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import city.olooe.plogging_project.model.RewardTypeStatus;
import org.apache.ibatis.javassist.compiler.ast.Member;

import city.olooe.plogging_project.model.MemberEntity;

import city.olooe.plogging_project.model.RewardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 이재원
 * 
 * @date : 2023.06.05
 * 
 * @brief : 리워드 관련 DTO 생성자, builder 사용
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardDTO {

    private Long rewardNo; // PK
    private String type; // 유형
    private String name; // 이름
    private String detail; // 내용

    public RewardDTO(final RewardEntity reward) {
        this.rewardNo = reward.getRewardNo();
        this.name = reward.getName();
        this.detail = reward.getDetail();
        this.type = reward.getType().getKey();
    }

    public static RewardEntity toEntity(final RewardDTO rewardDTO) {
        return RewardEntity.builder()
                .rewardNo(rewardDTO.getRewardNo())
                .name(rewardDTO.getName())
                .detail(rewardDTO.getDetail())
                .type(RewardTypeStatus.valueOf(rewardDTO.getType())).build();
    }

    /**
     * @author : 이재원
     * @date: 23.06.13     *
     * @param: -
     * @return: entity     *
     * @brief: DTO -> entity
     */
    public RewardEntity changeEntity() {
        return RewardEntity.builder()
                .rewardNo(rewardNo)
                .type(RewardTypeStatus.valueOf(getType()))
                .name(name)
                .detail(detail)
                .build();
    }
}
