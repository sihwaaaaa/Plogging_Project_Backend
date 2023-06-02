package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.UserMapEntity;

@Repository
/**
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: UserMapEntity jpa 구현체
 */
public interface UserMapRepository extends JpaRepository<UserMapEntity, String> {

}
