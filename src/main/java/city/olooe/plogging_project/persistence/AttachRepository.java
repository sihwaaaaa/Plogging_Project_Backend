package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.AttachEntity;
import city.olooe.plogging_project.model.community.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, Long> {
    Optional<AttachEntity> findByBno(BoardEntity bno);
}
