package city.olooe.plogging_project.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import city.olooe.plogging_project.model.DonationEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.ProductEntity;
import city.olooe.plogging_project.model.RewardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RewardDTO {

    // @Temporal(TemporalType.TIMESTAMP)
    // private LocalDateTime localDateTime;
    private Long rewardNo;
    private String type;
    private double tradePoint;
    private Date rewardDate;
    private Long memberNo;
    private Long dno;
    private Long pno;

    public RewardDTO(final RewardEntity entity) {
        this.rewardNo = entity.getRewardNo();
        this.type = entity.getType();
        this.tradePoint = entity.getTradePoint();
        this.rewardDate = entity.getRewardDate();
        this.dno = entity.getDonationEntity().getDno();
        this.pno = entity.getProductEntity().getPno();
    }

    public static RewardEntity toEntity(final RewardDTO dto) {
        return RewardEntity.builder()
                .rewardNo(dto.getRewardNo())
                .type(dto.getType())
                .tradePoint(dto.getTradePoint())
                .rewardDate(dto.getRewardDate())
                .memberEntity(MemberEntity.builder().memberNo(dto.getMemberNo()).build())
                .donationEntity(DonationEntity.builder().dno(dto.getDno()).build())
                .productEntity(ProductEntity.builder().pno(dto.getPno()).build())
                .build();
    }

}
