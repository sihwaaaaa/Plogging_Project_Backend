package city.olooe.plogging_project.persistence;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import city.olooe.plogging_project.model.ProductEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
/**
 * @author : 이재원
 * 
 * @date: 23.06.05
 * 
 * @brief: ProductRepository CRUD 테스트
 */
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 등록 테스트")
    public void createProduct() {
        ProductEntity productEntity = ProductEntity.builder()
                .name("새 상품 이름123")
                .detail("새 친환경 이름123")
                .build();
        log.info("{}", productRepository.save(productEntity));
    }

    @Test
    @DisplayName("전체 상품 조회 테스트")
    public void findAllProduct() {
        List<ProductEntity> product = productRepository.findAll();
        log.info("{}", product);
    }

    @Test
    @DisplayName("상품 이름 조회 테스트")
    public void findByProduct() {
        // List<ProductEntity> product = productRepository.findById(3L);
        log.info("{}", productRepository.findById(1L));
    }

    @Test
    @DisplayName("상품 수정 테스트")
    public void updateProduct() {
        ProductEntity productEntity = ProductEntity.builder().pno(1L)
                .name("수정된 상품이름")
                .detail("수정된 내용")
                .build();
        log.info("{}", productRepository.save(productEntity));
    }
}
