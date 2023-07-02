package city.olooe.plogging_project.model.friend;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_roomMember")
public class RoomMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rmemberNo;

    @ManyToOne(targetEntity = MessageRoomEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "roomNo", name = "roomNo")
    private MessageRoomEntity roomNo;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "memberNo", name = "memberNo")
    private MemberEntity memberNo;

}
