package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.PloggingEntity;
import city.olooe.plogging_project.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminService {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;
    private final PloggingRepository ploggingRepository;
    private final ChallengeRepository challengeRepository;
    private final BoardRepository boardRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final MapRepository mapRepository;

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 회원 리스트
     * @Brief 모든 회원 정보 확인
     */
    public List<MemberEntity> getMemberAll() {
        return memberRepository.findAll();
    }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 리워드 리스트
     * @Brief 모든 리워드 업체 정보 확인
     */
    public List<RewardEntity> getRewardAll() {return rewardRepository.findAll(); }

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 플로깅 리스트
     * @Brief 모든 플로깅 히스토리 정보 확인
     */
    public List<PloggingEntity> getPloggingAll() {return ploggingRepository.findAll();}

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 챌린지 리스트
     * @Brief 모든 챌린지 정보 확인
     */
    public List<ChallengeEntity> getChallengeAll() {return challengeRepository.findAll();}

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 게시글 리스트
     * @Brief 모든 게시글 정보 확인
     */
    public List<BoardEntity> getBoardAll() {return boardRepository.findAll();}

    /**
     * @Author 천은경
     * @Date 23.06.14
     * @return 포인트히스토리 리스트
     * @Brief 모든 포인트 히스토리 정보 확인
     */
    public List<PointHistoryEntity> getPointHistoryAll() {return pointHistoryRepository.findAll();}

    /**
     * @Author 천은경
     * @Date 23.06.15
     * @return 추천경로 리스트
     * @Brief 모든 추천경로 정보 확인
     */
    public List<MapEntity> getMapAll() {return mapRepository.findAll();}

    // 추천경로별 경유지 정보 확인
}
