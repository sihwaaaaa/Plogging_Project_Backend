package city.olooe.plogging_project.model.community;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BoardCategory {
    COMMUNITY("COMMUNITY", "커뮤니티"),
    PLOGGING("PLOGGING", "플로깅");

    @Getter
    private final String key;
    @Getter
    private final String value;

}
