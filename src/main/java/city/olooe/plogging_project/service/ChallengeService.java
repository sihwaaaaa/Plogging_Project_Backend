package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.*;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Slf4j
public class  ChallengeService {
    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;

    @Autowired
    ChallengeScheduleRepository challengeScheduleRepository;

    @Autowired
    SchedulMemberRepository schedulMemberRepository;

    @Autowired
    MemberRepository memberRepository;

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
    public List<ChallengeEntity> createChallenge(ChallengeDTO challengeDTO, Long memberNo) {

        MemberEntity host = MemberEntity.builder().memberNo(memberNo).build();
        ChallengeEntity challengeEntity = challengeDTO.toChallengeEntity();
        challengeEntity.setHost(host);
        challengeRepository.save(challengeEntity);

        // 챌린지리더가 챌린지 생성과 동시에 챌린지에 가입
        ChallengeMemberEntity challengeMemberEntity = new ChallengeMemberEntity();
        challengeMemberEntity.setChNo(challengeEntity);
        challengeMemberEntity.setChallenger(host);
        challengeMemberRepository.save(challengeMemberEntity);

        // return Long
        return challengeRepository.findAll();
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
         ChallengeDTO challengeDTO = new ChallengeDTO(challengeEntity);
         challengeDTO.setChallengers(challengeEntity.getChallengeMembers().stream().map(s -> s.getChallenger().getMemberNo()).collect(Collectors.toList()));
         return challengeDTO;
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
     * @date: '23.06.19
     *
     * @return: List
     *
     * @brief: 챌린지 맴버 전체 조회
     */
    @Transactional(readOnly = true)
    public List<ChallengeMemberDTO> chMemberList() {
        return challengeMemberRepository.findChallengeMemberEntityByOrderByChNoDesc().stream()
                .map(ChallengeMemberDTO::new)
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
     }

    /**
     * @author : 김민수
     * @date: '23.06.09
     * 
     * @param: chNo
     * 
     * @brief: 챌린지 가입 서비스
     */
    public ChallengeMemberEntity challengeJoin(ChallengeMemberDTO challengeMemberDTO, Long chNo, Long memberNo) {
        ChallengeMemberEntity challengeMemberEntity = challengeMemberDTO.toChallengeMemberEntity();
        challengeMemberEntity.setChNo(ChallengeEntity.builder().chNo(chNo).build());
        challengeMemberEntity.setChallenger(MemberEntity.builder().memberNo(memberNo).build());
        return challengeMemberRepository.save(challengeMemberEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: challengeScheduleDTO
     *
     * @brief: 챌린지 일정참여
     * @return: ChallengeScheduleEntity
     */
    @Transactional
    public SchedulMemberEntity scheduleJoin(ScheduleMemberDTO scheduleMemberDTO, Long memberNo){
        SchedulMemberEntity schedulMemberEntity = scheduleMemberDTO.toschedulMemberEntity();
        schedulMemberEntity.setChallenger(MemberEntity.builder().memberNo(memberNo).build());
//        schedulMemberEntity.setScheduleNo(ChallengeScheduleEntity.builder().scheduleNo(scheduleNo).build());
        return schedulMemberRepository.save(schedulMemberEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: chNo
     *
     * @brief: 챌린지 맴버 삭제서비스
     */
    public void cmemberDelete(ChallengeMemberDTO challengeMemberDTO){
        ChallengeMemberEntity challengeMemberEntity = challengeMemberDTO.toChMemberDelete();
        cmemberValidate(challengeMemberEntity);
        challengeMemberRepository.delete(challengeMemberEntity);
    }
    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: challengeScheduleDTO
     *
     * @brief: 챌린지 챌린지 일정생성
     * @return: ChallengeScheduleEntity
     */
    public ChallengeScheduleEntity scheduleCreate(ChallengeScheduleDTO challengeScheduleDTO){
        log.info("service {}", challengeScheduleDTO);
        ChallengeScheduleEntity challengeScheduleEntity = challengeScheduleDTO.toChallengeScheduleEntity();
        return challengeScheduleRepository.save(challengeScheduleEntity);
    }

    // 챌린지 리스트 조회
    @Transactional(readOnly = true)
    public List<ChallengeScheduleEntity> readChSchedule(ChallengeEntity chNo) {
        return challengeScheduleRepository.findByChNo(chNo);

    }

    // 스케쥴맴버 조회
    @Transactional(readOnly = true)
    public List<SchedulMemberEntity> readSchMemberList(ChallengeScheduleEntity ScheduleNo) {
        return schedulMemberRepository.findByScheduleNo(ScheduleNo);
    }



    /**
     * @author : 김민수
     * @date: '23.06.11
     *
     * @param: challengeScheduleDTO
     *
     * @brief: 챌린지 일정참여 취소
     * @return: ChallengeScheduleEntity
     */
    @Transactional
    public void scheduleCancle(Long smno){
        // 챌린지 일정참여 취소 - 챌린지 일정No
        SchedulMemberEntity schedulMemberEntity = schedulMemberRepository.findBySmno(smno);
        schedulMemberRepository.delete(schedulMemberEntity);
    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param: challengeScheduleDTO
     *
     * @brief: 챌린지 일정삭제
     * @return: ChallengeScheduleEntity
     */
//    public void scheduleDelete(Long smno, ChallengeScheduleDTO challengeScheduleDTO){
//        // 챌린지 일정삭제 - 스케쥴No, 챌린지No 필요
//        // 일정에 참여한 인원있다면 해당인원 일정참여 취소 시킨후 삭제진행
//        SchedulMemberEntity schedulMemberEntity = schedulMemberRepository.findBySmno(smno);
//        if(schedulMemberEntity != null){
//            schedulMemberEntity.getSmno();
//            schedulMemberEntity.getChNo();
//            schedulMemberEntity.getChallenger();
//            schedulMemberEntity.getScheduleNo();
//            schedulMemberRepository.delete(schedulMemberEntity);
//        }
//        ChallengeScheduleEntity chSchDelete = challengeScheduleDTO.toChallengeDelete();
//        challengeScheduleRepository.delete(chSchDelete);
//    }

    /**
     * @author : 김민수
     * @date: '23.06.16
     *
     * @param:
     *
     * @brief: 회원이 가입한 챌린지 목록
     * @return:
     */
//    public List<ChallengeEntity> memberChList(Long memberNo){
//        MemberEntity member = memberRepository.findByMemberNo(memberNo);
//        List<ChallengeEntity> challengeEntities = challengeRepository.findMyChallenges(member);
//        System.out.println("challengeEntities: " + challengeEntities);
//        return challengeEntities;
//    }

    /**
     * @author : 김민수
     * @date: '23.06.08
     *
     * @param:
     * @return: List
     *
     * @brief: 해당챌린지에 가입한 맴버들 조회
     */
//    @Transactional(readOnly = true)
//    public List<ChallengeMemberEntity> chMembers(ChallengeMemberDTO challengeMemberDTO) {
//        ChallengeMemberEntity challengeMemberEntities = challengeMemberDTO.toChNo();
//        return challengeMemberRepository.findByChNo(challengeMemberEntities);
//    }


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
