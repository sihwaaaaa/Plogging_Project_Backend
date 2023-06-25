package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, Long> {
}
