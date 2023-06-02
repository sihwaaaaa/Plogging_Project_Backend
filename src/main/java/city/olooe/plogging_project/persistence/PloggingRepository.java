package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.PloggingEntity;

@Repository
/**
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: PloggingEntity jpa 구현체
 */
public interface PloggingRepository extends JpaRepository<PloggingEntity, String> {

}
