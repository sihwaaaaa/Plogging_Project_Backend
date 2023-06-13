package city.olooe.plogging_project.persistence;

import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.AuthEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
  // 권한 저장
}
