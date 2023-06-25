package city.olooe.plogging_project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : 이재원
 * @date: 23.06.09
 * @brief: 리워드 Type에 따라 연산 작업
 */
@RequiredArgsConstructor
public enum RewardTypeStatus {
    Challenge("Challenge", 100L), // 챌린지
    Plogging("Plogging", 10L), // 플로깅
    Donation("Donation", -1000L), // 기부
    Product("Product", -8000L); // 랜덤박스

    @Getter
    private final String key;
    @Getter
    private final Long value;

    private static final Map<String, Long> KEY_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(RewardTypeStatus::getKey, RewardTypeStatus::getValue)));

    public static RewardTypeStatus of(final String key) {
        return RewardTypeStatus.valueOf(KEY_MAP.get(key).toString());
    }
}