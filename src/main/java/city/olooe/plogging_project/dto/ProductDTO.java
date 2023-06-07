package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: 랜덤박스 구성품 DTO
 */
public class ProductDTO {

    private Long pno; // PK, 상품 번호
    private String name; // 상품 이름
    private String detail; // 상품 번호
    private Long proPrice = 8000L;

    /**
     * @author : 이재원
     * @date: 23.06.05
     * 
     * @param: ProductEntity
     * @return: void
     * 
     * @brief: entity -> DTO
     */
    public ProductDTO(final ProductEntity productEntity) {
        this.pno = productEntity.getPno();
        this.name = productEntity.getName();
        this.detail = productEntity.getDetail();
        // this.proPrice = getProPrice();
    }

    /**
     * @author : 이재원
     * @date: 23.06.05
     * 
     * @param: ProductDTO
     * @return: pno, name, detail
     * 
     * @brief: entity -> DTO
     */
    public static ProductEntity productEntity(final ProductDTO productDTO) {
        return ProductEntity.builder()
                .pno(productDTO.getPno())
                .name(productDTO.getName())
                .detail(productDTO.getDetail())
                .build();
    }
}
