package city.olooe.plogging_project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import city.olooe.plogging_project.model.BadgeEntity;

/**
 * @author: 이재원
 * @date: 2023.06.07
 * @brief: BadgeEntity CURD 구현체
 */
public interface BadgeRepository extends JpaRepository<BadgeEntity, Long> {

}
