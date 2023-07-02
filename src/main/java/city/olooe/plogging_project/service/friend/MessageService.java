package city.olooe.plogging_project.service.friend;

import city.olooe.plogging_project.dto.friend.MessageDTO;
import city.olooe.plogging_project.dto.friend.MessageRoomDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.friend.MessageEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import city.olooe.plogging_project.model.friend.RoomMemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.persistence.MessageRepository;
import city.olooe.plogging_project.persistence.MessageRoomRepository;
import city.olooe.plogging_project.persistence.RoomMemberRepository;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final MemberRepository memberRepository;

    /**
     * @Author 천은경
     * @Date 23.07.02
     * @return 모든 메시지 리스트
     * @Brief 모든 메시지 확인하기
     */
    @Transactional(readOnly = true)
    public List<MessageDTO> getList() {
        List<MessageEntity> messageEntities = messageRepository.findAll();
        return messageEntities.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    /**
     * @Author 천은경
     * @Date 23.07.02
     * @param roomNo
     * @param pageable
     * @return 페이징된 메시지 리스트
     * @Brief 해당 메시지룸의 모든 메시지 리스트 페이징 보기
     */
    @Transactional(readOnly = true)
    public Page<MessageDTO> messagesOfRoom(Long roomNo, Pageable pageable) {
        Page<MessageEntity> messageEntities = messageRepository.findByRoomNoOrderByMsgNoDesc(
                MessageRoomEntity.builder().roomNo(roomNo).build(), pageable);
        return messageEntities.map(MessageDTO::new);
    }

    /**
     * @Author 천은경
     * @Date 23.0.02
     * @param memberNo
     * @return 메시지룸 리스트
     * @Brief 해당 회원의 모든 메시지룸 리스트 보기
     */
    @Transactional(readOnly = true)
    public List<MessageRoomDTO> roomOfMember(MemberEntity memberNo){
        List<MessageRoomEntity> roomEntities = messageRoomRepository.findByMember(memberNo);
        return roomEntities.stream().map(MessageRoomDTO::new).collect(Collectors.toList());
    }

    /**
     *
     * @Author 천은경
     * @Date 23.07.02
     * @param user
     * @param receiverNo
     * @param pageable
     * @return 페이징된 메시지 리스트
     * @Brief 메시지룸 만들기
     */
    public MessageRoomDTO createRoom(ApplicationUserPrincipal user, Long receiverNo, Pageable pageable) {
        Optional<MemberEntity> receiver = memberRepository.findById(receiverNo);

        Optional<RoomMemberEntity> room = roomMemberRepository.findRoom(user.getMember().getMemberNo(), receiverNo);

        if(!room.isPresent()) {
            // 메시지룸 생성
            MessageRoomEntity messageRoomEntity =
                    messageRoomRepository.save(MessageRoomEntity.builder()
                            .roomName(user.getMember().getUserName() + ", " + receiver.orElseThrow(NullPointerException::new).getUserName())
                            .build());
            // 메시지룸의 멤버 생성
            roomMemberRepository.save(RoomMemberEntity.builder().memberNo(user.getMember()).roomNo(messageRoomEntity).build());
            roomMemberRepository.save(RoomMemberEntity.builder().memberNo(receiver.get()).roomNo(messageRoomEntity).build());

            // 해당 메시지룸의 모든 메시지 목록 반환
            return new MessageRoomDTO(messageRoomEntity);
        }
        
        // 기존 메시지룸의 모든 메시지 목록 반환
        return new MessageRoomDTO(room.get().getRoomNo());
    }

    /**
     * @Author 천은경
     * @Date 23.07.02
     * @param messageEntity
     * @param pageable
     * @return 페이징된 메시지 리스트
     * @Brief 메시지 보내기
     */
    public Page<MessageDTO> createMessage(MessageEntity messageEntity, Pageable pageable) {
        messageRepository.save(messageEntity);
        return messageRepository.findByRoomNoOrderByMsgNoDesc(messageEntity.getRoomNo(), pageable).map(MessageDTO::new);
    }
}
