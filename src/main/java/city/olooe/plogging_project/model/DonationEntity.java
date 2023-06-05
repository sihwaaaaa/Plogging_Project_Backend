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
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_donation")
/**
 * @author : 이재원
 * 
 * @date : 2023.06.05
 * 
 * @brief : 기부처 Entity
 */
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno; // 기부처 번호
    private String name; // 기부처 명(재단 명)
    private String detail; // 기부처 내용(지원내용이나 지원대상)
}
