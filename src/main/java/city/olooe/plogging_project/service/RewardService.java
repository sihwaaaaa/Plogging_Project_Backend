package city.olooe.plogging_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.RewardEntity;
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
    private MemberService memberService; // 회원가입 성공 시 +50p 적립

    /**
     * @author: 이재원
     * @date: 2023.06.05
     * @brief: 리워드 적립
     * @param: rewardEntity
     * @return: RewardEntity.save(rewardEntity)
     */
    public RewardEntity create(final RewardEntity rewardEntity) {
        // 1. 회원가입 성공 시 포인트 적립

        // 2. 플로깅에 참여하면 10p

        // 3. 챌린지에 참여하면 100p
        return null;
    }
}
