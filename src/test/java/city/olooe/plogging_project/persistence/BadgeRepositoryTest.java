package city.olooe.plogging_project.persistence;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.BadgeEntity;



@SpringBootTest
@Slf4j
public class BadgeRepositoryTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @Test
    @DisplayName("뱃지 생성 Test")
    public void createBadge() {
        BadgeEntity badgeEntity = BadgeEntity.builder()
                .name("뱃지 이름")
                .point(2000L)
                .build();
//        log.info("뱃지 생성 Test : {}", badgeRepository.save(badgeEntity));
    }

    @Test
    @DisplayName("뱃지 수정 Test")
    public void updateBadge() {
        BadgeEntity badgeEntity = BadgeEntity.builder()
                .name("수정된 뱃지 이름")
                .point(-1000L)
                .build();
//        log.info("{}", badgeRepository.save(badgeEntity));
    }

    @Test
    @DisplayName("뱃지 전체 조회 Test")
    public void findAllBadge() {
        List<BadgeEntity> badgeEntities = badgeRepository.findAll();

//        log.info("{}", badgeEntities);
    }

    @Test
    @DisplayName("뱃지 단일 조회 Test")
    public void findByBadge() {
        log.info("{}", badgeRepository.findById(1L));
    }

    @Test
    @DisplayName("뱃지 삭제 Test")
    public void deleteBadge() {
        BadgeEntity badgeEntity = badgeRepository.getReferenceById(2L);
        badgeRepository.delete(badgeEntity);
    }
}
