package city.olooe.plogging_project.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.ibatis.javassist.compiler.ast.Member;

import city.olooe.plogging_project.model.DonationEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.ProductEntity;
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

    // @Temporal(TemporalType.TIMESTAMP)
    // private LocalDateTime localDateTime;
    private Long rewardNo; // PK, Reward 번호
    private String type; // 포인트 유형(기부 or 랜덤박스)
    private Long tradePoint; // 포인트 증감, 차감액
    private Date rewardDate; // 포인트 업데이트 시간
    private Long memberNo; // FK, 회원 번호
    private Long dno; // FK, 기부 번호
    private Long pno; // FK, 상품 번호

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
