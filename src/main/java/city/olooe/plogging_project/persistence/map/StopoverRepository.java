package city.olooe.plogging_project.persistence.map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.map.StopoverEntity;

@Repository
/**
 * @author : 이시화
 * 
 * @date: 23.06.01
 * 
 * @brief: StopoverEntity jpa 구현체
 */
public interface StopoverRepository extends JpaRepository<StopoverEntity, String> {
  /**
  * @author : 이시화
  * @date: 23.06.08
  * 
  * @param: mapNo
  * @return: StopoverEntity
  * 
  * @brief:mapNo로 Stopover 단일 객체 반환
  */
  // StopoverEntity findByMapNo(Integer mapNo);s
}
