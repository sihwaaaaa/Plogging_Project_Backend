package city.olooe.plogging_project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : 김민수
 * @date: 23.06.09
 * @brief: 챌린지 상태 타입
 */
@RequiredArgsConstructor
public enum ChallengeStatus {
    // 챌린지 진행전 / 진행중 / 인원마감 / 챌린지 종료

    CHALLENGEBEFORE("CHALLENGEBEFORE", "진행전"), // 진행전
    CHALLENGEGOING("CHALLENGEGOING", "진행중"), // 진행중
    CLOSEINGPERSONNEL("CLOSE", "인원마감"), // 인원마감
    CHALLENGECLOSE("CLOSE", "챌린지 종료"); // 챌린지 종료

    @Getter
    private final String key;
    @Getter
    private final String value;

}
