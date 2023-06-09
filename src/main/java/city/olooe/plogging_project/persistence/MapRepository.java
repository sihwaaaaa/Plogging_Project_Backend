package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.MapEntity;
import java.util.List;


/**
 * @author : 이시화
 * 
 * @date: 2023.06.01
 * 
 * @brief: MapEntity jpa 인터페이스 구현체
 */
@Repository
public interface MapRepository extends JpaRepository<MapEntity, Long> {
  /**
  * @author : 이시화
  * @date: 23.06.08
  * 
  * @param: tmp(임시pk)
  * @return: List<MapEntity>
  * 
  * @brief: tmp를 통해서 mapEntity 리스트 조회 - 데이터 파싱위한 메서드
  */
  List<MapEntity> findByTmp(String tmp);
}
