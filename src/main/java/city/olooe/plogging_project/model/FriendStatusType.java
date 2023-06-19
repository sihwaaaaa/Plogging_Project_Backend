package city.olooe.plogging_project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : 천은경
 * @date: 23.06.05
 * @brief: 플친 상태 타입.
 */
@RequiredArgsConstructor
public enum FriendStatusType {
    PENDING("PENDING", "플친요청대기"), // 플친 요청 대기
    FRIEND("FRIEND", "플친중"), // 플친
    BLOCK("BLOCK", "차단"), // 차단
    NOTHING("NOTHING", "비플친"); //비플친

    @Getter
    private final String key;
    @Getter
    private final String value;

}
