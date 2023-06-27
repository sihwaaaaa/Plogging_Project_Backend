package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.friend.FriendDTO;
import city.olooe.plogging_project.model.friend.FriendEntity;
import city.olooe.plogging_project.model.friend.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.FriendRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : 천은경
 * @date: 23.06.05
 * @brief: Friend 테이블 서비스 로직
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FriendService {

    private final FriendRepository friendRepository;


    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param fromMemberNo
     * @param toMemberNo
     * @return FriendEntity
     * @Brief 플친 신청
     */
    public List<FriendEntity> requestFriend(Long fromMemberNo, Long toMemberNo) {
        // Entity 생성
        MemberEntity fromMember = toEntityWithNo(fromMemberNo);
        MemberEntity toMember = toEntityWithNo(toMemberNo);
        FriendEntity friendEntity = new FriendEntity(fromMember, toMember);
        // 유효성 검사
        validate(friendEntity);
        // 차단 확인 및 상대의 플친 신청 여부 후 저장
        checkBlocked(friendEntity);
        // 신청 리스트 반환
        return friendRepository.findByFromMemberAndStatus(fromMember,
                FriendStatusType.PENDING);
    }

    /**
     * @Author 천은경
     * @Date 23.06.06
     * @return 플친 리스트
     * @Brief 플친 전체 조회
     */
    public List<FriendEntity> allFriend() {
        return friendRepository.findAll();
    }

    /**
     * @Author 천은경
     * @Date 23.06.25
     * @param fromMember
     * @param toMember
     * @return 플친 Object
     * @Brief 플친 단일 조회
     */
    public Optional<FriendEntity> getFriend(Long fromMember, Long toMember) {
        return friendRepository.findByFromMemberAndToMember
                (MemberEntity.builder().memberNo(fromMember).build(), MemberEntity.builder().memberNo(toMember).build());
    }


    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param memberNo
     * @param status
     * @return List<FriendEntity>
     * @Brief 나의 팔로잉 상태별 플친 리스트 조회 : 요청대기 / 플친중 / 차단
     */
    @Transactional(readOnly = true)
    public List<FriendEntity> GetMyFriendList(Long memberNo, String status) {

        return friendRepository.findByFromMemberAndStatus(toEntityWithNo(memberNo),
                FriendStatusType.valueOf(status));
    }

    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param memberNo
     * @param status
     * @return List<FriendEntity>
     * @Brief 나의 팔로워 상태별 플친 리스트 조회 : 요청대기 / 플친중 / 차단
     */
    @Transactional(readOnly = true)
    public List<FriendEntity> getFriendListToMe(Long memberNo, String status){
        MemberEntity fromMember = toEntityWithNo(memberNo);

        return friendRepository.findByToMemberAndStatus(fromMember, FriendStatusType.valueOf(status));
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param toMemberNo
     * @param fromMemberNo
     * @Brief 플친 요청 수락
     */
    public List<FriendEntity> acceptRequest(Long toMemberNo, Long fromMemberNo) {
        // 현재 로그인 유저 엔티티 변환
        MemberEntity toMember = toEntityWithNo(toMemberNo);
        // 상대와의 플친 확인
        Optional<FriendEntity> friendEntity = friendRepository.findByFromMemberAndToMember(toEntityWithNo(fromMemberNo), toMember);
        // 차단 확인 후 수락
        checkBlocked(friendEntity.orElseThrow(NullPointerException::new).setFriend());
        // 플친 요청 수락
//        friendRepository.save(friendEntity.get().setFriend());
        // 수락 후 나에게 온 요청 리스트 반환
        return friendRepository.findByToMemberAndStatus(toMember, FriendStatusType.PENDING);
    }

    /**
     * @Author 천은경
     * @Date 23.06.13
     * @param fromMemberNo
     * @param toMemberNo
     * @return 팔로잉 요청 리스트 or 차단 리스트
     * @Brief 보낸 플친 요청 취소하기 or 차단 취소 하기
     */
    public List<FriendEntity> cancelRequest(Long fromMemberNo, Long toMemberNo) {
        // 현재 로그인 유저 엔티티 변환
        MemberEntity fromMember = toEntityWithNo(fromMemberNo);
        // 상대와의 플친 확인
        Optional<FriendEntity> friendEntity = friendRepository.findByFromMemberAndToMember(fromMember, toEntityWithNo(toMemberNo));

        // 플친 요청 취소 or 차단 취소 (데이터 삭제)
        friendRepository.delete(friendEntity.orElseThrow(NullPointerException::new));

        // 요청 취소일 경우 요청리스트 반환, 차단 취소일 경우 차단리스트 반환
        List<FriendEntity> returnList = new ArrayList<>();
        if(friendEntity.get().getStatus().equals(FriendStatusType.BLOCK)){
            returnList = friendRepository.findByFromMemberAndStatus(fromMember, FriendStatusType.BLOCK);
        } else if(friendEntity.get().getStatus().equals(FriendStatusType.PENDING)) {
            returnList = friendRepository.findByFromMemberAndStatus(fromMember, FriendStatusType.PENDING);
        }

        return returnList;
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param toMemberNo
     * @param fromMemberNo
     * @return 나의 플친 리스트
     * @Brief 플친 요청 거절 or 플친 삭제
     */
    public List<FriendEntity> removeFriend(Long toMemberNo, Long fromMemberNo) {

        // 현재 로그인 유저 엔티티 변환
        MemberEntity toMember = toEntityWithNo(toMemberNo);
        // 플친 상태 확인
        Optional<FriendEntity> onFriend =
                friendRepository.findByFromMemberAndToMember(toEntityWithNo(fromMemberNo), toMember);

        // 팔로워와 이미 플친인 경우는 요청 거절이 아닌 플친 삭제이기 때문에, fromMember와 toMember가 교차된 데이터도 삭제
        if(onFriend.isPresent() && onFriend.get().getStatus().equals(FriendStatusType.FRIEND)){
          Optional<FriendEntity> convertedEntity = friendRepository.findByFromMemberAndToMember(toMember, toEntityWithNo(fromMemberNo));
          friendRepository.delete(convertedEntity.orElseThrow(NumberFormatException::new));
        }

        // 플친 요청 거절 -> friendEntity 삭제
        friendRepository.delete(onFriend.orElseThrow(NullPointerException::new));

        // 삭제된 후, 나의 플친 리스트 반환
        return friendRepository.findByToMemberAndStatus(toMember, FriendStatusType.PENDING);
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param fromMemberNo
     * @param toMemberNo
     * @return 나의 플친 리스트
     * @Brief 상대방 차단. 상대방과 플친인 경우 또는 상대방이 요청 중인 경우 상대방에게서는 플친 삭제
     */
    public List<FriendEntity> blockFriend(Long fromMemberNo, Long toMemberNo) {

        MemberEntity fromMember = toEntityWithNo(fromMemberNo);

        // 나에게서 차단
        Optional<FriendEntity> friendEntity = friendRepository.findByFromMemberAndToMember(fromMember, toEntityWithNo(toMemberNo));
        // 차단시 나에게서 관계가 존재하는 경우
        friendEntity.ifPresent(entity -> entity.setStatus(FriendStatusType.BLOCK));
        // 차단시 나에게서 관계가 아직 존재하지 않는 경우
        friendEntity.orElse(friendRepository.save(
                FriendEntity.builder().fromMember(fromMember).toMember(toEntityWithNo(toMemberNo))
                        .status(FriendStatusType.BLOCK).build()));

        // 상대방에게서 관계가 존재하면서, 관계가 플친중 또는 요청대기인 경우, 상대방에게서는 친구 삭제
        Optional<FriendEntity> convertedEntity = friendRepository.findByFromMemberAndToMember(toEntityWithNo(toMemberNo), fromMember);
        if(convertedEntity.isPresent() && convertedEntity.get().getStatus() != FriendStatusType.BLOCK) {
            friendRepository.delete(convertedEntity.get());
        }

//        if(friendRepository.findByFromMemberAndToMember(toEntityWithNo(toMemberNo), fromMember).isPresent()
//                && friendRepository.findByFromMemberAndToMember(toEntityWithNo(toMemberNo), fromMember)
//                .get().getStatus() != FriendStatusType.BLOCK){
//            friendRepository
//                    .delete(friendRepository.findByFromMemberAndToMember(toEntityWithNo(toMemberNo), fromMember).get());
//        }

        // 나의 플친리스트 반환
        return friendRepository.findByFromMemberAndStatus(fromMember, FriendStatusType.FRIEND);
    }

    /**
     * @author 천은경
     * @date 23.06.06
     * @param friendEntity
     * 플친 유효성 검사
     */
    private void validate(final FriendEntity friendEntity) {
        // fromMember 부존재
        if (friendEntity.getFromMember() == null) {
            throw new RuntimeException("플친 요청자가 존재하지 않는 회원입니다.");
        }
        // toMember 부존재
        else if (friendEntity.getToMember() == null) {
            throw new RuntimeException("플친 응답자가 존재하지 않는 회원입니다.");
        }
        // fromMember와 toMember가 같을 경우
        else if (friendEntity.getFromMember() == friendEntity.getToMember()) {
            throw new RuntimeException("본인과 플친을 맺을 수 없습니다.");
        }
        // 내가 차단한 경우
        else if(friendEntity.getStatus() == FriendStatusType.BLOCK) {
            throw new RuntimeException("이미 차단한 상대입니다.");
        }
    }

    /**
     * @Author 천은경
     * @Date 23.06.27
     * @param friendEntity
     * @Brief 차단 당한 경우 플친 제한
     */
    private void checkBlocked(final FriendEntity friendEntity) {
        Optional<FriendEntity> converted = friendRepository
                .findByFromMemberAndToMember(friendEntity.getToMember(), friendEntity.getFromMember());

        // 이미 상대방이 나에게 플친 신청한 경우, 바로 플친
        if (converted.isPresent() && converted.get().getStatus() == FriendStatusType.PENDING) {
            friendRepository.save(converted.get().setFriend());
        }
        // 차단 당한 경우를 제외하고 플친 신청
        else if(!converted.isPresent() || converted.get().getStatus() != FriendStatusType.BLOCK){
            friendRepository.save(friendEntity);
        }
    }

    /**
     * @Authoer 천은경
     * @Date 23.06.07
     * @param memberNo
     * @return MemberEntity
     * @Brief memberNo로 MemberEntity를 반환하는 메서드
     */
    private MemberEntity toEntityWithNo(Long memberNo) {
        return MemberEntity.builder().memberNo(memberNo).build();
    }

}
