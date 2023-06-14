package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.BoardDTO;
import city.olooe.plogging_project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import city.olooe.plogging_project.dto.ChallengeDTO;
import city.olooe.plogging_project.dto.ChallengeMemberDTO;
import city.olooe.plogging_project.persistence.ChallengeMemberRepository;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.ChallengeScheduleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class  ChallengeService {
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

//        LocalDate regDate =  challengeDTO.getRegDate();
//        LocalDate start = challengeDTO.getStartDate();
//        int result = regDate.compareTo(start);
//
//        if(result == 0)
//            ChallengeStatus.CHALLENGEBEFORE.getValue();
//        else if (result < 0)
//            ChallengeStatus.CHALLENGEGOING.getValue();
//        else
//            ChallengeStatus.CHALLENGECLOSE.getValue();

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
     public ChallengeDTO searchByChNo(Long chNo) {
     ChallengeEntity challengeEntity = challengeRepository.findByChNo(chNo);
     validate(challengeEntity);
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
    public List<ChallengeDTO> serchAllCh() {
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
         // 챌린지 맴버삭제, 챌린지 일정삭제 , 챌린지 일정에 참여중인 참여삭제
     ChallengeEntity challengeEntity = challengeRepository.findByChNo(chNo);
     validate(challengeEntity);
     challengeRepository.delete(challengeEntity);
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
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: chNo
     *
     * @brief: 챌린지 맴버 삭제서비스
     */
    public void cmemberDelete(ChallengeEntity chNo, MemberEntity memberNo ){
        ChallengeMemberEntity challengeMemberEntity = challengeMemberRepository.findByChNoAndChallenger(chNo,memberNo);
        cmemberValidate(challengeMemberEntity);
        challengeMemberRepository.delete(challengeMemberEntity);
    }



    /**
     * @author 김민수
     * @date 23.06.09
     * @param challengeEntity
     * @brief: 챌린지 유효성 Null 검사
     */
    private void validate(final ChallengeEntity challengeEntity) {
//        Date regDate =  challengeEntity.getRegDate();
//        Date start = challengeEntity.getStartDate();
//        int result = regDate.compareTo(start);

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
        // personnel null
        else if (challengeEntity.getPersonnel() == null) {
            throw new RuntimeException("empty personnel");
        } else if (challengeEntity.getPersonnel() < 2 && challengeEntity.getPersonnel() > 10) {
            throw new RuntimeException("error personnel");
//        // result
//        } else if(result == 0){
//            ChallengeStatus.CHALLENGEBEFORE.getValue();
//        } else if (result < 0){
//            ChallengeStatus.CHALLENGEGOING.getValue();
//        } else
//            ChallengeStatus.CHALLENGECLOSE.getValue();
        }
    }

    /**
     * @author 김민수
     * @date 23.06.09
     * @param ChallengeMemberEntity
     * @brief: 챌린지 member Null 검사
     */
    private void cmemberValidate(final ChallengeMemberEntity challengeMemberEntity) {
        // member null
        if (challengeMemberEntity.getChNo() == null) {
            throw new RuntimeException("chNo member");
        }
        // title null
        else if (challengeMemberEntity.getChallenger() == null) {
            throw new RuntimeException("Challenger title");
        }

    }

}
