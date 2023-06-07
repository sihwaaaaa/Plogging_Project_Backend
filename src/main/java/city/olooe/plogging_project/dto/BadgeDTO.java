package city.olooe.plogging_project.dto;

import city.olooe.plogging_project.model.BadgeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDTO {
    private Long badgeNo; // 뱃지 번호
    private String name; // 뱃지 이름(등급)
    private Long point; // 포인트(현재 포인트 계산에 필요)

    public BadgeDTO(final BadgeEntity badgeEntity) {
        this.badgeNo = badgeEntity.getBadgeNo();
        this.name = badgeEntity.getName();
        this.point = badgeEntity.getPoint();
    }

    public static BadgeEntity badgeEntity(final BadgeDTO badgeDTO) {
        return BadgeEntity.builder()
                .badgeNo(badgeDTO.getBadgeNo())
                .name(badgeDTO.getName())
                .point(badgeDTO.getPoint())
                .build();
    }
}
