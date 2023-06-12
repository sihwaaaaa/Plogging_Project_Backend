package city.olooe.plogging_project.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;

@Repository
/**
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: StopoverEntity jpa 구현체
 */
public interface StopoverRepository extends JpaRepository<StopoverEntity, Long> {
  // List<StopoverEntity> findByMapEntity(MapEntity mapEntity);
}
