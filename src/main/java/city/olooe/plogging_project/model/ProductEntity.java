package city.olooe.plogging_project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")

/**
 * @author : 이재원
 * 
 * @date : 2023.06.05
 * 
 * @brief : 랜덤박스 구성품 Entity
 */
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno; // PK, 상품 번호
    private String name; // 상품 이름
    private String detail; // 상품 내용(가격은 필요x, 어떤 소재로 만들었는지에 대한 내용)
}
