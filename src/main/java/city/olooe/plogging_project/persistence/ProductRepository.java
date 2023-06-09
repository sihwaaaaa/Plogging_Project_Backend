package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import city.olooe.plogging_project.model.ProductEntity;

/*
* @author : 이재원
* 
* @date: 23.06.05
* 
* @brief: 기본 CURD 구현을 위한 ProductEntity jpa 구현체
*/
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
