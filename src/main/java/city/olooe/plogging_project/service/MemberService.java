package city.olooe.plogging_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 박연재
 * @date: 2023.06.02
 * @brief: 비즈니스 계층의 회원 서비스
 */
@Service
@Slf4j
public class MemberService {

  @Autowired
  private MemberRepository memberRepository; // 멤버 jpa 구현체 빈 등록

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
//    memberEntity.set(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
    return memberRepository.save(memberEntity);
  }

  /**
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 확인 용도
   * @param: MemberEntity
   * @return: MemberEntity
   */
  public MemberEntity getByCredentials(final String userId, final String password, PasswordEncoder encoder) {
      final MemberEntity originalMember = memberRepository.findByUserId(userId)
              .orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다."));

      if(originalMember != null && encoder.matches(password, originalMember.getPassword())) return originalMember; // 회원이 존재하지 않으면 0
      
      return null;

  }


}
