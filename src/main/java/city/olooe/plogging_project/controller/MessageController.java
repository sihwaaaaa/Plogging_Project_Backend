package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.dto.friend.MessageDTO;
import city.olooe.plogging_project.dto.friend.MessageRoomDTO;
import city.olooe.plogging_project.model.friend.MessageEntity;
import city.olooe.plogging_project.model.friend.MessageRoomEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.friend.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @GetMapping("rooms")
    public ResponseEntity<?> getMyMessageRoom(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<MessageRoomDTO> messageRoomDTOS = messageService.roomOfMember(user.getMember());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(messageRoomDTOS).build());
    }

    @GetMapping("messages/{roomNo}")
    public ResponseEntity<?> messagesOfRoom(@PathVariable Long roomNo,
                                            @PageableDefault(size = 30) Pageable pageable){
        Page<MessageDTO> messageDTOS = messageService.messagesOfRoom(roomNo, pageable);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(messageDTOS).build());
    }

    @PostMapping("/create/room")
    public ResponseEntity<?> createRoom(@AuthenticationPrincipal ApplicationUserPrincipal user,
                                        @RequestBody MemberDTO receiver, @PageableDefault(size = 30) Pageable pageable) {

        MessageRoomDTO messageRoomDTO = messageService.createRoom(user, receiver.getMemberNo(), pageable);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(messageRoomDTO).build());
    }

    @PostMapping("/create/message")
    public ResponseEntity<?> createMessage(@AuthenticationPrincipal ApplicationUserPrincipal user,
                                           @RequestBody MessageDTO messageDTO,
                                           @PageableDefault(size = 30) Pageable pageable) {
        MessageEntity messageEntity = MessageEntity.builder()
                .content(messageDTO.getContent())
                .roomNo(MessageRoomEntity.builder().roomNo(messageDTO.getRoomNo()).build())
                .sender(user.getMember())
                .build();

        Page<MessageDTO> messageDTOS = messageService.createMessage(messageEntity, pageable);
        return ResponseEntity.ok().body(ResponseDTO.builder().data(messageDTOS).build());
    }
}
