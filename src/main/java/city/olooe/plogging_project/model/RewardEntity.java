package city.olooe.plogging_project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_reward")
/*
 * @author : 이재원
 * 
 * @date: 2023.06.01
 * 
 * @brief: 회원 포인트 클래스
 */
public class RewardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardNo; // pk
    private String type; // 리워드 유형(기부, 상품)
    private double tradePoint; // 포인트의 증감, 차감
    private Date rewardDate; // 포인트 갱신 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo", nullable = false)
    private MemberEntity memberEntity; // memberNo를 통해 조회

    @ManyToOne
    @JoinColumn(name = "dno")
    private DonationEntity donationEntity; // FK, 기부 번호

    @ManyToOne
    @JoinColumn(name = "pno")
    private ProductEntity productEntity; // FK, 상품 번호

}
