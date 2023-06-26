package city.olooe.plogging_project.persistence;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import city.olooe.plogging_project.model.ChallengeEntity;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;

import java.util.List;
//import city.olooe.plogging_project.model.ChallengeMemberEntity;

/**
 * @author: 박연재
 * @date: 2023.06.01
 * @brief: MemberEntity의 jpa 인터페이스
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  /**
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 로그인 메서드
   * @param userId
   * @param password
   * @return MemberEntity
   */
  MemberEntity findByUserIdAndPassword(String userId, String password);

  /**
   * @author: 박연재
   * @param: userId
   * @return: MemberEntity
   */
  MemberEntity findByUserId(String userId);

  /**
   * @author: 박연재
   * @param: userId
   * @return: MemberEntity
   */
  MemberEntity findByUserName(String userName);

  /**
   * @author: 박연재
   * @brief: 아이디 찾기를 위한 회원 조회
   * @param: userId
   * @return: MemberEntity
   */
  MemberEntity findByUserNameAndEmail(String userName, String email);

  MemberEntity findByMemberNo(Long memberNo);

  /**
   * 
   * 
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 존재 여부 (회원 가입시에 중복 여부)
   * @param userId
   * @return Boolean
   */
  Boolean existsByUserId(String userId);

  /**
   * 
   * 
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 존재 여부 (회원 가입시에 중복 여부)
   * @param userId
   * @return Boolean
   */
  Boolean existsByUserName(String userName);

  /**
   * 
   * 
   * @author: 박연재
   * @date: 2023.06.02
   * @brief: 회원 존재 여부 (회원 가입시에 중복 여부)
   * @param userId
   * @return Boolean
   */
  Boolean existsByEmail(String email);

  /**
   * @Author 천은경
   * @Date 23.06.15
   * @param keyword
   * @param pageable
   * @return 회원 리스트
   * @Brief userId, userName, nickName 으로 회원 검색
   */
  Page<MemberEntity> findByUserIdContainingIgnoreCase(String keyword, Pageable pageable);

  // /**
  // * @author: 박연재
  // * @date: 2023.06.02
  // * @brief: 회원 수정
  // * @param memberEntity
  // * @return void
  // */
  // @Modifying
  // void update(final MemberEntity memberEntity);

  // /**
  // * @author: 박연재
  // * @date: 2023.06.02
  // * @brief: 회원 수정
  // * @param memberEntity
  // * @return void
  // */
  // @Modifying
  // void delete(Long memberNo);
}
