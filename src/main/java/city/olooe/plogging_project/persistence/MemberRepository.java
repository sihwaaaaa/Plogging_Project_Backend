package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.MemberEntity;

/**
 * @author: 박연재
 * @date: 2023.06.01
 * @brief: MemberEntity의 jpa 인터페이스
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  MemberEntity findByUserIdAndPassword(String userId, String password);
}
