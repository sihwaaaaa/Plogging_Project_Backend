package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.MapEntity;

/**
 * @author : 이시화
 * 
 * @date: 2023.06.01
 * 
 * @brief: MapEntity jpa 인터페이스 구현체
 */
@Repository
public interface MapRepository extends JpaRepository<MapEntity, Long> {

}
