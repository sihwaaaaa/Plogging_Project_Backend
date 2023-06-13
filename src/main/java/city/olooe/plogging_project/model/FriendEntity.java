package city.olooe.plogging_project.model;

import city.olooe.plogging_project.persistence.FriendRepository;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/*
 * @author : 천은경
 * @date: 23.06.02
 * @brief: 플친 테이블 Entity
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
@Table(name = "tbl_friend")
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendNo; // pk

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "fromMember", referencedColumnName = "memberNo")
    private MemberEntity fromMember;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemberEntity.class)
    @JoinColumn(name = "toMember", referencedColumnName = "memberNo")
    private MemberEntity toMember;

    @Enumerated(EnumType.STRING)
    @Setter
    private FriendStatusType status; // 플친 요청 대기 / 플친중 / 차단 상태 표시

    /**
     * @author 천은경
     * @Date 23.06.07
     * @param fromMember
     * @param toMember
     * @Brief 친구 요청 기능 구현. 이때 status값은 PENDING으로 기본 설정.
     */
    public FriendEntity(MemberEntity fromMember, MemberEntity toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.status = FriendStatusType.PENDING;
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @Return FriendEntity
     * @Brief FriendEntity의 status를 FRIEND로 변경, fromMember와 toMember가 교차된 FriendEntity를 생성함으로써 친구 요청 수락 기능 구현
     */
    public FriendEntity setFriend() {
        this.setStatus(FriendStatusType.FRIEND);
        return FriendEntity.builder().fromMember(this.getToMember()).toMember(this.getFromMember())
                .status(FriendStatusType.FRIEND).build();
    }


}
