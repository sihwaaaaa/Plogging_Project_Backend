package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RewardServiceTest {
    @Autowired
    private RewardService rewardService;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 기부처 기부하기
     * @param: rewardEntity, rewardService
     * @return: MemberNo, currentPoint, tradePoint
     */
    @DisplayName("리워드 전체 조회 Test")
    @Test
    @Transactional
    public void testRewardlist() {
        // RewardEntity rewardEntity = RewardEntity.builder()
        // .memberEntity(MemberEntity.builder().memberNo(1L).totalPoint(1000L).build());
        // rewardEntities =
        // RewardEntity.builder().memberEntity(MemberEntity.builder().memberNo(1L).totalPoint().build());

        // RewardEntity rewardEntity =
        // RewardEntity.builder().memberEntity(MemberEntity.builder().memberNo(1L).build())
        // .tradePoint(-8000L).build();
        // rewardEntities = rewardService.findAllReward();
        // log.info("{}", rewardEntities);
    }

    @Test
    @Transactional
    public void testmemberrank() {

    }
    // RewardEntity rewardEntity =
    // RewardEntity.builder().memberEntity(MemberEntity.builder().memberNo(1L).build())
    // .tradePoint(-8000L).build();

    // rewardService.joinProduct(rewardEntity);
    // log.info("{}", rewardEntity);
    // log.info("test : {}", rewardService.findAllProduct().get(0));

    @DisplayName("랜덤박스 구성품 조회 Test")
    @Test
    @Transactional
    public void testProduct() {
    }

    @DisplayName("기부처 전체 조회 Test")
    @Test
    @Transactional
    public void testfindAllDonation() {
    }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 랜덤박스 신청
     * @param: rewardEntity, rewardService
     * @return: MemberNo, currentPoint, tradePoint
     */
    @DisplayName("랜덤박스 신청 생성 Test")
    @Test
    @Transactional
    @Rollback(false)
    public void testJoinProduct() {
    }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 기부처 기부하기
     * @param: rewardEntity, rewardService
     * @return: MemberNo, currentPoint, tradePoint
     */
    @DisplayName("기부 하기 Test")
    @Test
    @Transactional
    @Rollback(false)
    public void testJoinDonation() {

    }
    // MemberEntity member =
    // MemberEntity.builder().memberNo(rewardEntity.getMemberEntity().getMemberNo())
    // .currentPoint(rewardEntity.getMemberEntity().getCurrentPoint() - 8000)
    // .address(rewardEntity.getMemberEntity().getAddress())
    // .build();
    // memberRepository.save(member);
    // memberEntity = memberService.create(new MemberEntity(1L, 8000L, "홍길동",
    // "aaa"));

    // rewardEntity = rewardService.joinProduct(rewardEntity);
    // memberRepository.save(memberEntity);
    // log.info("{}", rewardService.joinProduct(rewardEntity));
    // memberEntity =
    // MemberEntity.builder().memberNo(1L).currentPoint(8000L).address("서울").build();
    // .memberEntity(MemberEntity.builder().memberNo(dto.getMemberNo()).build())
    // .donationEntity(DonationEntity.builder().dno(dto.getDno()).build())
    // .productEntity(ProductEntity.builder().pno(dto.getPno()).build())
    // entity = rewardService.joinProduct();
    // List<?> rewardEntities = rewardService.findAllProduct();
    // List<?> rewardEntities = new ArrayList<>();
    // rewardEntities = rewardService.findAllProduct();

    // MemberEntity member =
    // MemberEntity.builder().memberNo(rewardEntity.getMemberEntity().getMemberNo())
    // .currentPoint(rewardEntity.getMemberEntity().getCurrentPoint() -
    // 8000).build();
    // memberRepository.save(member);
    // log.info("{}", rewardEntities);
    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 랜덤박스 조회 및 신청
     * @param: memberEntity, productEntity
     * @return: donationEntity.findAll , reward.save(update)
     */
    // @DisplayName("랜덤박스 구성품 조회 및 신청")
    // @Test
    // public void findProduct(final ProductEntity productEntity, final MemberEntity
    // memberEntity) {
    // int productPrice = -8000;

    // // 랜덤박스 구성품 조회를 위한 출력(Select)
    // List<ProductEntity> productEntities = productRepository.findAll();
    // for (ProductEntity productEntity2 : productEntities) {
    // log.info("상품 조회 : {}", productEntity2);
    // }

    // RewardEntity rewardEntity = RewardEntity.builder()
    // .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo())
    // .address(memberEntity.getAddress()).build())
    // .type("랜덤박스 신청")
    // .tradePoint(productPrice)
    // .build();
    // log.info("랜덤박스 신청 Test : {}", rewardRepository.save(rewardEntity));
    // }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 기부처 조회 및 신청
     * @param: memberEntity, productEntity
     * @return: donationEntity.findAll , reward.save(update)
     */
    // public void findDonation(final ProductEntity productEntity, final
    // MemberEntity memberEntity,
    // final DonationEntity donationEntity) {
    // int donationPrice = -1000;
    // // 기부처 조회를 위한 출력(Select)
    // List<DonationEntity> donationEntities = donationRepository.findAll();
    // for (DonationEntity donationEntity2 : donationEntities) {
    // log.info("{}", donationEntity2);
    // }

    // RewardEntity rewardEntity = RewardEntity.builder()
    // .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo()).build())
    // .type("기부")
    // .tradePoint(donationPrice)
    // .build();
    // log.info("기부 Test : {}", rewardRepository.save(rewardEntity));
    // // return rewardRepository.save(rewardEntity);
    // }
}
