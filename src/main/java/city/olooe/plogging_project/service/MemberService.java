package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.member.MemberSearchDTO;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.persistence.ChallengeRepository;
import city.olooe.plogging_project.persistence.FriendRepository;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.model.AuthEntity;
import city.olooe.plogging_project.model.AuthType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.FriendStatusType;
import city.olooe.plogging_project.persistence.AuthRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;

/**
 * @author: 박연재
 * @date: 2023.06.02
 * @brief: 비즈니스 계층의 회원 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

  private final MemberRepository memberRepository; // 멤버 jpa 구현체 빈 등록
  private final AuthRepository authRepository;
  private final ChallengeRepository challengeRepository;
  private final FriendRepository friendRepository;

  /**
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 생성
   * @param: memberEntity
   * @return: memberRepository.save(memberEntity)
   */
  public MemberEntity create(final MemberEntity memberEntity) {
    if (memberEntity == null || memberEntity.getUserId() == null) {
      throw new RuntimeException("유효하지 않은 인자값");
    }

    final String userId = memberEntity.getUserId();

    if (memberRepository.existsByUserId(userId)) {
      String msg = "회원 아이디가 이미 존재합니다";
      log.warn(msg + "{}", userId);
      throw new RuntimeException(msg);
    }
    // memberEntity.set(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
    return memberRepository.save(memberEntity);
  }

  /**
   * @author 박연재
   * @date 23.06.23
   * @brief 프로필 조회를 위한 회원 조회
   * @param memberEntity
   * @return MemberEntity
   */
  public MemberEntity getMember(final MemberEntity memberEntity) {
    return memberRepository.findByUserId(memberEntity.getUserId());
  }

  /**
   * @author 박연재
   * @date 23.06.23
   * @brief 상대 프로필 조회용 메서드
   * @param memberNo
   * @return
   */
  public Optional<MemberEntity> getMember(Long memberNo) {
    return memberRepository.findById(memberNo);
  }

  public void createAuth(final MemberEntity member) {
    AuthEntity auth = new AuthEntity(member, AuthType.ROLE_MEMBER);
    authRepository.save(auth);
  }

  /**
   * @author 박연재
   * @date 23.06.23
   * @brief 회원 정보 수정
   * @param userId
   * @param password
   * @param encoder
   * @return
   */
  @Transactional
  public void modify(MemberDTO member) {
    MemberEntity registeredMember = memberRepository.findById(member.getMemberNo())
        .orElseThrow(() -> new RuntimeException("회원을 발견하지 못함"));
    registeredMember.setNickName(member.getNickName());
    registeredMember.setUserName(member.getUserName());
    registeredMember.setBirth(member.getBirth());
    registeredMember.setAddressDetail(member.getAddressDetail());
    registeredMember.setGender(member.getGender());
    registeredMember.setIntro(member.getIntro());
  }

  /**
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 확인 용도
   * @param: MemberEntity
   * @return: MemberEntity
   */
  public MemberEntity getByCredentials(final String userId, final String password, PasswordEncoder encoder) {
    final MemberEntity originalMember = memberRepository.findByUserId(userId);

    if (originalMember != null && encoder.matches(password, originalMember.getPassword()))
      return originalMember; // 회원이 존재하지 않으면 0

    return null;

  }

  /**
   * @Author 천은경
   * @Date 23.06.15
   * @param user
   * @param keyword
   * @param pageable
   * @return 회원 리스트
   * @Brief userId or userName or nickName 으로 회원 검색
   */
  public Page<MemberSearchDTO> searchMember(ApplicationUserPrincipal user, String keyword, Pageable pageable) {

    Page<MemberEntity> searchMemberEntity = memberRepository.findByUserIdContainingIgnoreCase(keyword, pageable);

    Page<MemberSearchDTO> memberDTOS = searchMemberEntity.map(member -> {
      MemberSearchDTO memberSearchDTO = new MemberSearchDTO(member);
      Pageable top3 = PageRequest.of(0, 3);
      memberSearchDTO.setChallenges(challengeRepository.findMyChallenges(member, top3)
          .stream().map(ChallengeEntity::getTitle).collect(Collectors.toList()));
      memberSearchDTO.setFriendStatus(friendRepository.findStatusBy(user.getMember(), member)
          .orElse(FriendStatusType.NOTHING).getKey());
      return memberSearchDTO;
    });

    return memberDTOS;
  }

  public void validateWithUserId(String userId) throws Exception {
    List<MemberEntity> memberList = memberRepository.findAll();
    Boolean isExistUserId = memberList.stream().map(member -> member.getUserId())
        .anyMatch(existUserId -> userId == existUserId);
    if (isExistUserId) {
      throw new Exception("중복되는 회원 아이디가 존재합니다.");
    }
  }

  /**
   * @author 박연재
   * @throws Exception
   * @date 2023.06.16
   * @brief 회원 유효성 검증
   */
  public void validateWithMember(final MemberEntity member, MemberDTO dto) throws Exception {
    if (member.getUserId() == dto.getUserId() || member.getEmail() == dto.getEmail()) {
      throw new Exception("중복되는 값이 존재합니다.");
    }
  }

}
