package city.olooe.plogging_project.service;

import java.util.List;

import javax.transaction.Transactional;

import city.olooe.plogging_project.model.RewardTypeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.RewardEntity;
//import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.RewardRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 이재원
 * @date: 2023.06.12
 * @brief: 리워드 서비스(비즈니스 계층)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    /**
     * @Author 이재원
     * @Date 23.06.13
     * @param rewardNo
     * @return List<RewardEntity>
     * @Brief 리워드 내역 전체 조회
     */
    @Transactional
    public List<RewardEntity> GetRewardList() {
        return rewardRepository.findAll(Sort.by(Direction.DESC, "rewardNo"));
    }

    /**
     * @Author 이재원
     * @Date 23.06.13
     * @param RewardStatus.type
     * @return List<RewardEntity>
     * @Brief 포인트 유형 별 조회
     */
    // @Transactional
    // public RewardEntity getReward(Long id) {
    // return rewardRepository.findById(id).orElseThrow();
    // }

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
}
