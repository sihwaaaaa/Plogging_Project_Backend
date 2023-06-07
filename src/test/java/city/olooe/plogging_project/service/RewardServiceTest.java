package city.olooe.plogging_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.DonationEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.ProductEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.persistence.DonationRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.persistence.ProductRepository;
import city.olooe.plogging_project.persistence.RewardRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RewardServiceTest {
    @Autowired
    private RewardRepository rewardRepository; // 리워드 jpa 구현체를 Bean등록!

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ProductRepository productRepository; // 랜덤박스 구성품 jpa 구현체를 Bean 등록!

    @Autowired
    private MemberRepository memberRepository; // 회원 정보 jpa 구현체 bean 등록

    /**
     * @author: 이재원
     * @date: 2023.06.05
     * @brief: 리워드 적립
     * @param: rewardEntity
     * @return: RewardEntity.save(rewardEntity)
     */

    // 랜덤박스 조회 및 사용
    public RewardEntity findProduct(final ProductEntity productEntity, final MemberEntity memberEntity,
            final DonationEntity donationEntity) {
        int productPrice = -8000;
        // 랜덤박스 구성품 조회를 위한 출력(Select)
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity productEntity2 : productEntities) {
            log.info("{}", productEntity2);
        }

        RewardEntity rewardEntity = RewardEntity.builder()
                .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo())
                        .address(memberEntity.getAddress()).build())
                .type("랜덤박스 신청")
                .tradePoint(productPrice)
                .build();
        return rewardRepository.save(rewardEntity);
    }

    // 기부처 조회 및 사용
    public RewardEntity findDonation(final ProductEntity productEntity, final MemberEntity memberEntity,
            final DonationEntity donationEntity) {
        int donationPrice = -1000;
        // 기부처 조회를 위한 출력(Select)
        List<DonationEntity> donationEntities = donationRepository.findAll();
        for (DonationEntity donationEntity2 : donationEntities) {
            log.info("{}", donationEntity2);
        }

        RewardEntity rewardEntity = RewardEntity.builder()
                .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo()).build())
                .type("기부하기")
                .tradePoint(donationPrice)
                .build();
        return rewardRepository.save(rewardEntity);
    }
}
