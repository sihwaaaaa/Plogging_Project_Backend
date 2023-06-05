package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ChallengeMemberEntity;

@Repository
public interface ChallengeMemberRepository extends JpaRepository<ChallengeMemberEntity, Long> {
    ChallengeMemberEntity findByCmemberNo(Long cmemberNo);
}
