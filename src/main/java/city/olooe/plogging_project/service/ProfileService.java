package city.olooe.plogging_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.persistence.BoardRepository;
import city.olooe.plogging_project.persistence.ChallengeMemberRepository;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.ChallengeScheduleRepository;
import city.olooe.plogging_project.persistence.PloggingRepository;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfileService {

  @Autowired
  private ChallengeRepository challengeRepository;

  @Autowired
  private PointHistoryRepository pointHistoryRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private PloggingRepository ploggingRepository;

  @Autowired
  private ChallengeMemberRepository challengeMemberRepository;

  @Autowired
  private ChallengeScheduleRepository challengeScheduleRepository;

  public List<ChallengeEntity> searchByMemberNo(Long memberNo) {
    return challengeMemberRepository.findByCmemberNo(memberNo);

  }

}
