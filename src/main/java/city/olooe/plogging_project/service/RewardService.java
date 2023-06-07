package city.olooe.plogging_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.DonationDTO;
import city.olooe.plogging_project.model.DonationEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.ProductEntity;
import city.olooe.plogging_project.model.RewardEntity;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.DonationRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.ProductRepository;
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

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    static ProductRepository productRepository; // 랜덤박스 구성품 jpa 구현체를 Bean 등록!

    @Autowired
    private MemberRepository memberRepository; // 회원 정보 jpa 구현체 bean 등록

    // @Autowired
    // private ChallengeRepository challengeRepository; // 챌린지 jpa 구현체를 Bean 등록

    // @Autowired
    // private PloggingRepository ploggingRepository; // 플로깅 jpa 구현체를 Bean 등록

    /**
     * @author: 이재원
     * @date: 2023.06.05
     * @brief: 리워드 적립
     * @param: rewardEntity
     * @return: RewardEntity.save(rewardEntity)
     */
    public RewardEntity create(final RewardEntity rewardEntity) {

        return null;
    }

    // ProductEntity를 RewardENtity로 수정.
    public RewardEntity randomBox(final ProductEntity productEntity, final MemberEntity memberEntity) {
        int productPrice = 8000;
        // 랜덤박스 구성품 조회를 위한 출력(Select)
        List<ProductEntity> productEntities = productRepository.findAll();

        // 기부처 조회를 위한 출력(Select)
        List<DonationEntity> donationEntities = donationRepository.findAll();

        // 랜덤박스 신청 한다.(Save)
        // 배송지, 포인트 차감액
        RewardEntity rewardEntity = RewardEntity.builder()
                .memberEntity(MemberEntity.builder().memberNo(memberEntity.getMemberNo())
                        .address(memberEntity.getAddress()).build())
                .type("랜덤박스 신청")
                .tradePoint(productPrice)
                .build();

        return rewardEntity;
    }

    public static void main(String[] args) {
        // List<ProductEntity> productEntities = productRepository.findAll();

        // log.info("{}", productEntities);
        // System.out.println(productEntities);

        // 생성 Test

    }
}
