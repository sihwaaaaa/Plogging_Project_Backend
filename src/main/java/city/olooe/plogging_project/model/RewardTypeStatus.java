package city.olooe.plogging_project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : 이재원
 * @date: 23.06.09
 * @brief: 리워드 Type에 따라 연산 작업
 */
@RequiredArgsConstructor
public enum RewardTypeStatus {
    Challenge("Challeange", "챌린지"), // 100
    Plogging("Plogging", "플로깅"), // 10
    Donation("Donation", "기부하기"), // -1000
    Product("Product", "랜덤박스"); // -8000

    @Getter
    private final String key;
    @Getter
    private final String value;
}