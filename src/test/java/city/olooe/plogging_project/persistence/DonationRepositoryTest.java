package city.olooe.plogging_project.persistence;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.DonationEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
/**
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: DonationRepository CRUD 테스트
 */
public class DonationRepositoryTest {

    @Autowired
    private DonationRepository donationRepository;

    @Test
    @DisplayName("기부처 등록 테스트")
    public void createDonation() {
        DonationEntity donationEntity = DonationEntity.builder()
                .dno(1L)
                .name("기부처 이름 TEST")
                .detail("나")
                .build();
        log.info("{}", donationRepository.save(donationEntity));
    }

    @Test
    @DisplayName("기부처 전체 조회 테스트")
    public void findAllDonation() {
        List<DonationEntity> donationlist = donationRepository.findAll();
        log.info("{}", donationlist);
    }

    @Test
    @DisplayName("기부처 이름 조회 테스트")
    public void findByIdDonation() {
        log.info("{}", donationRepository.findById(1L));
    }

    @Test
    @DisplayName("기부처 수정 테스트")
    public void updateDonation() {
        DonationEntity donationEntity = DonationEntity.builder()
                .dno(1L)
                .name("기부처 이름 수정")
                .detail("기부처 내용 수정")
                .build();

        log.info("{}", donationRepository.save(donationEntity));
    }
}
