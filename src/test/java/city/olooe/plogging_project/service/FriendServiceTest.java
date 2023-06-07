//package city.olooe.plogging_project.service;
//
//import city.olooe.plogging_project.model.FriendEntity;
//import city.olooe.plogging_project.model.MemberEntity;
//import city.olooe.plogging_project.persistence.FriendRepository;
//import city.olooe.plogging_project.persistence.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//
//@SpringBootTest
//@Slf4j
//class FriendServiceTest {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    FriendRepository friendRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    FriendService friendService;
//
//    @Autowired
//    MemberService memberService;

//    @Test
//    public void test나에게친구중_특정상황_PENDING() {
//        MemberEntity mem = memberRepository.findById(2L).orElseThrow(() -> new RuntimeException("없는 회원"));
//        friendRepository.findByToMemberAndStatus(mem, FriendStatus.PENDING).forEach(f -> {
//        f.setStatus(FriendStatus.FRIEND);
//        log.info("{}", f);
//        friendRepository.save(f.convert());
//        });
//    }

//
//    @Test
//    void 플친요청() {
//        // given
//        MemberEntity fromMember = memberService.create(new MemberEntity("olooe", "1234", "응공", "olooe@olooe.city"));
//        logger.warn("from {}", fromMember);
//        MemberEntity toMember = memberService.create(new MemberEntity("seolha", "1234", "서라", "seolha@olooe.city"));
//        logger.warn("to {}", toMember);
//
//        FriendEntity friendEntity = friendService.requestFriend(new FriendEntity(fromMember, toMember));
//        logger.warn("friend {} ", friendEntity);
//        // when
//        //Long savedFriendId = friendService.requestFriend(friendEntity).getFriendNo();
//
//        // then
//        //assertEquals(friendEntity, friendRepository.findByFriendNo(savedFriendId));
//    }
//
//    @Test
//    void 나의플친리스트() {
//        MemberEntity member = memberRepository.findByUserId("olooe");
//        List<FriendEntity> friends = friendService.GetFriendList(member);
//
//        logger.warn("플친 : {}", friends.size());
//    }
//
//    @Test
//    void 내가한요청리스트() {
//        MemberEntity member = memberRepository.findByUserId("olooe");
//        List<FriendEntity> requestList = friendService.getMyRequestList(member);
//        logger.warn("요청리스트 : {}", requestList.size());
//    }
//
//
//    @Test
//    void 받은요청리스트() {
//        MemberEntity member = memberRepository.findByUserId("olooe");
//        List<FriendEntity> requestList = friendService.getRequestListToMe(member);
//        logger.warn("요청리스트 : {}", requestList.size());
//    }
//
//    @Test
//    void 나의차단리스트() {
//        MemberEntity member = memberRepository.findByUserId("olooe");
//        List<FriendEntity> blockedList = friendService.getBlockedList(member);
//        logger.warn("차단리스트 : {}", blockedList.size());
//    }
//
//    @Test
//    void 친구상태조회() {
//        MemberEntity fromMember = memberRepository.findByUserId("olooe");
//        MemberEntity toMember = memberRepository.findByUserId("seolha");
//        String status = friendService.getStatus(fromMember, toMember);
//        logger.warn("친구상태 : {}", status);
//    }
//}