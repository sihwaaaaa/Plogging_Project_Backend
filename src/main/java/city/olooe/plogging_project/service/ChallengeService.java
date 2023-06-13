package city.olooe.plogging_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ChallengeMemberDTO;
import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.ChallengeStatus;
import city.olooe.plogging_project.persistence.ChallengeMemberRepository;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.ChallengeScheduleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChallengeService {
    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    ChallengeScheduleRepository challengeScheduleRepository;

    /**
     * @author : 김민수
     * @date: '23.06.08
     * 
     * @param: ChallengeDTO
     * @return: challengeDTO
     * 
     * @brief: 챌린지 생성
     */
    @Transactional
    public ChallengeEntity createChallenge(ChallengeDTO challengeDTO) {

        // 유효성 검사
        validate(challengeDTO.toChallengeEntity());

        // return Long
        return challengeRepository.save(challengeDTO.toChallengeEntity());
    }

    /**
     * @author : 김민수
     * @date: '23.06.08
     * 
     * @param: chNo
     * @return: challengeRepository
     * 
     * @brief: 챌린지 단일 조회
     */
    @Transactional(readOnly = true)
    public ChallengeDTO searchByBno(Long chNo) {
        ChallengeEntity challengeEntity = challengeRepository.findByChNoOptional(chNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 챌린지가 존재하지 않습니다."));

        return new ChallengeDTO(challengeEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.08
     * 
     * @return: List
     * 
     * @brief: 챌린지 전체 조회
     */
    @Transactional(readOnly = true)
    public List<ChallengeDTO> searchAllBoard() {
        return challengeRepository.findChallengeEntityByOrderByChNoDesc().stream()
                .map(ChallengeDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * @author : 김민수
     * @date: '23.06.08
     * 
     * @param: chNo
     * 
     * @brief: 챌린지 삭제 서비스
     */
    @Transactional
    public void delete(Long chNo) {
        ChallengeEntity boardEntity = challengeRepository.findByChNoOptional(chNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 챌린지는 존재하지 않습니다."));
        challengeRepository.delete(boardEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.09
     * 
     * @param: chNo
     * 
     * @brief: 챌린지 가입 서비스
     */
    public Long challengeJoin(ChallengeMemberDTO challengeMemberDTO) {
        return challengeMemberRepository.save(challengeMemberDTO.toChallengeMemberEntity()).getCmemberNo();
    }

    /**
     * @author 김민수
     * @date 23.06.09
     * @param challengeEntity
     * @brief: 챌린지 유효성 Null 검사
     */
    private void validate(final ChallengeEntity challengeEntity) {
        // member null
        if (challengeEntity.getHost() == null) {
            throw new RuntimeException("empty member");
        }
        // title null
        else if (challengeEntity.getTitle() == null) {
            throw new RuntimeException("empty title");
        }
        // content null
        else if (challengeEntity.getContent() == null) {
            throw new RuntimeException("empty content");
        }
        // personnel error
        else if (challengeEntity.getPersonnel() == null) {
            throw new RuntimeException("empty personnel");
        } else if (challengeEntity.getPersonnel() > 2 && challengeEntity.getPersonnel() < 10) {
            throw new RuntimeException("error personnel");
        }
        // status null
        else if (challengeEntity.getStatus() == null) {
            throw new RuntimeException("empty status");
        }
    }

}
