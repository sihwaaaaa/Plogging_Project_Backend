package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.DonationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
/**
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: 기부처 DTO
 */
public class DonationDTO {

    private Long dno; // PK, 상품 번호
    private String name; // 상품 이름
    private String detail; // 상품 번호

    /**
     * @author : 이재원
     * @date: 23.06.05
     * 
     * @param: DonationEntity
     * @return: void
     * 
     * @brief: entity -> DTO
     */
    public DonationDTO(final DonationEntity donationEntity) {
        this.dno = donationEntity.getDno();
        this.name = donationEntity.getName();
        this.detail = donationEntity.getDetail();
    }

    /**
     * @author : 이재원
     * @date: 23.06.05
     * 
     * @param: DonationDTO
     * @return: void
     * 
     * @brief: entity -> DTO
     */
    public static DonationEntity donationEntity(final DonationDTO donationDTO) {
        return DonationEntity.builder()
                .dno(donationDTO.getDno())
                .name(donationDTO.getName())
                .detail(donationDTO.getDetail())
                .build();
    }

}
