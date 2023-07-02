package city.olooe.plogging_project.model.friend;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_messageRoom")
public class MessageRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    private String roomName;

    @OneToMany(mappedBy = "roomNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> messageList = new ArrayList<>();

    @OneToMany(mappedBy = "roomNo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomMemberEntity> roomMembers = new ArrayList<>();

}
