package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
//import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.RewardRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 이재원
 * @date: 2023.06.05
 * @brief: 리워드 서비스(비즈니스 계층)
 */
@Service
@Slf4j
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository; // 리워드 jpa 구현체를 Bean등록!

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 랜덤박스 신청
     * @param: memberEntity(회원 번호, 현재 포인트)
     * @return: memberRepository(memberEntity)
     */
    @Transactional
    public RewardEntity joinProduct(RewardEntity rewardEntity) {

        // member.changeCurrentPoint(rewardEntity.getTradePoint());

        // memberPointEntity.changeCurrentPoint(rewardEntity.getTradePoint());
        // memberRepository.save(member);

        return rewardRepository.save(rewardEntity);
    }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 기부하기
     * @param: 회원 번호, 현재 포인트
     * @return: memberRepository(memberEntity)
     */
    @Transactional
    public RewardEntity joinDonation(RewardEntity rewardEntity) {
        // 회원의 포인트 1000p 차감(update)
        // 차감된 데이터 기록 (insert)

        return rewardRepository.save(rewardEntity);
    }

    /**
     * @author: 이재원
     * @date: 2023.06.08
     * @brief: 리워드 단일 조회
     * @param: Long id
     * @return: rewardNo
     */
    @Transactional
    public RewardEntity getReward(Long id) {
        return rewardRepository.findById(id).orElseThrow();
    }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 랜덤박스 구성품 조회
     * @param: pno, attach(IMG 미구현)
     * @return: productEntities
     */
    // @Transactional
    // public List<ProductEntity> findAllProduct() {
    // return productRepository.findAll(Sort.by(Direction.DESC, "pno"));
    // }

    /**
     * @author: 이재원
     * @date: 2023.06.07
     * @brief: 기부처 전체 조회
     * @param: pno, attach(IMG 미구현)
     * @return: productEntities
     */
    // @Transactional
    // public List<DonationEntity> findAllDonation() {
    // return donationRepository.findAll(Sort.by(Direction.DESC, "dno"));
    // }

    /**
     * @author: 이재원
     * @date: 2023.06.08
     * @brief: 랭킹 서비스
     * @param: pno, attach(IMG 미구현)
     * @return: productEntities
     */
    // @Transactional
    // public MemberPointEntity memberRank(RewardEntity rewardEntity) {
    // MemberPointEntity memberPointEntity = MemberPointEntity.builder()
    // .totalPoint(rewardEntity.getMemberPointEntity().getTotalPoint()).build();
    // return null;
    // }

    /**
     * @author: 이재원
     * @date: 2023.06.08
     * @brief: 리워드 전체 조회
     * @param:
     * @return: rewardNo
     */
    @Transactional
    public List<RewardEntity> findAllReward() {
        return rewardRepository.findAll(Sort.by(Direction.DESC, "rewardNo"));
    }
    // public RewardEntity joinProduct(MemberEntity memberEntity) {
    // return null;
    // }

    // 누구의 포인트 8천차감 (update)
    // 차감된 데이터 기록 (insert)
    // MemberEntity member =
    // MemberEntity.builder().memberNo(rewardEntity.getMemberEntity().getMemberNo())
    // .currentPoint(rewardEntity.getMemberEntity().getCurrentPoint() -
    // 8000).build();
    // memberRepository.save(member);

    // return rewardRepository.save(rewardEntity);
    // 회원의 포인트 적립 방법
    // 1. 회원가입 2. 플로깅 참여 성공 여부 3. 챌린지 참여 성공 여부

    // 회원이 리워드 페이지에서 조회 데이터
    // 1. 랭킹
    // - 자신의 등급(뱃지) - 누적 포인트

    // 2. 랜덤박스
    // - 현재 포인트(회원_CurrentPoint) - 상품 리스트 조회

    // 3. 기부
    // - 기부처 리스트 조회

    //

    // // 랜덤박스 조회 및 사용
    // public RewardEntity findProduct(final ProductEntity productEntity, final
    // MemberEntity memberEntity,
    // final DonationEntity donationEntity) {
    // int productPrice = -8000;
    // // 랜덤박스 구성품 조회를 위한 출력(Select)
    // List<ProductEntity> productEntities = productRepository.findAll();
    // for (ProductEntity productEntity2 : productEntities) {
    // log.info("{}", productEntity2);
    // }

    // RewardEntity rewardEntity = RewardEntity.builder()
    // .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo())
    // .address(memberEntity.getAddress()).build())
    // .type("랜덤박스 신청")
    // .tradePoint(productPrice)
    // .build();
    // return rewardRepository.save(rewardEntity);
    // }

    // // 기부처 조회 및 사용
    // public RewardEntity findDonation(final ProductEntity productEntity, final
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
    // .type("기부하기")
    // .tradePoint(donationPrice)
    // .build();
    // return rewardRepository.save(rewardEntity);
    // }
    // 누구의 포인트 8천차감 (update)
    // 차감된 데이터 기록 (insert)
    // MemberEntity member =
    // MemberEntity.builder().memberNo(rewardEntity.getMemberEntity().getMemberNo())
    // .currentPoint(rewardEntity.getMemberEntity().getCurrentPoint() - 8000L)
    // .build();
}
