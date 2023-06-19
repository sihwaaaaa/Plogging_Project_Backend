package city.olooe.plogging_project.service;

import city.olooe.plogging_project.model.FriendEntity;
import city.olooe.plogging_project.model.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.FriendRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
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
    private final MemberRepository memberRepository;


    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param userId
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
        //validate(friendEntity);
        // 저장
        friendRepository.save(friendEntity);
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
     * @Date 23.06.06
     * @param userId
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
     * @param userId
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
     * @param userId
     * @param fromMemberNo
     * @Brief 플친 요청 수락
     */
    public List<FriendEntity> acceptRequest(Long toMemberNo, Long fromMemberNo) {
        // 현재 로그인 유저 엔티티 변환
        MemberEntity toMember = toEntityWithNo(toMemberNo);
        // 상대와의 플친 확인
        FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(toEntityWithNo(fromMemberNo), toMember);
        // 플친 요청 수락
        friendRepository.save(friendEntity.setFriend());
        // 수락 후 나에게 온 요청 리스트 반환
        return friendRepository.findByToMemberAndStatus(toMember, FriendStatusType.PENDING);
    }

    /**
     * @Author 천은경
     * @Date 23.06.13
     * @param userId
     * @param toMemberNo
     * @return 팔로잉 요청 리스트 or 차단 리스트
     * @Brief 보낸 플친 요청 취소하기 or 차단 취소 하기
     */
    public List<FriendEntity> cancelRequest(Long fromMemberNo, Long toMemberNo) {
        // 현재 로그인 유저 엔티티 변환
        MemberEntity fromMember = toEntityWithNo(fromMemberNo);
        // 상대와의 플친 확인
        FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(fromMember, toEntityWithNo(toMemberNo));
//        FriendEntity friendEntity = FriendEntity.builder().fromMember(fromMember).toMember(MemberEntity.builder().memberNo(toMemberNo).build()).build();

        // 플친 요청 취소 or 차단 취소 (데이터 삭제)
        friendRepository.delete(friendEntity);

        // 반환 객체
        // 요청 취소일 경우 요청리스트 반환, 차단 취소일 경우 차단리스트 반환
        List<FriendEntity> returnList = new ArrayList<>();
        if(friendEntity.getStatus().equals(FriendStatusType.BLOCK)){
            returnList = friendRepository.findByFromMemberAndStatus(fromMember, FriendStatusType.BLOCK);
        } else if(friendEntity.getStatus().equals(FriendStatusType.PENDING)) {
            returnList = friendRepository.findByFromMemberAndStatus(fromMember, FriendStatusType.PENDING);
        }

        return returnList;
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param userId
     * @param fromMemberNo
     * @return 나의 플친 리스트
     * @Brief 플친 요청 거절 or 플친 삭제
     */
    public List<FriendEntity> removeFriend(Long toMemberNo, Long fromMemberNo) {

        // 현재 로그인 유저 엔티티 변환
        MemberEntity toMember = toEntityWithNo(toMemberNo);
        // 팔로워 상태 확인
        FriendEntity onFriend = friendRepository.findByFromMemberAndToMember(toEntityWithNo(fromMemberNo), toMember);

        // 팔로워와 이미 플친인 경우는 요청 거절이 아닌 플친 삭제이기 때문에, fromMember와 toMember가 교차된 데이터도 삭제
        if(onFriend.getStatus().equals(FriendStatusType.FRIEND)){
          FriendEntity convertedEntity = friendRepository.findByFromMemberAndToMember(toMember, toEntityWithNo(fromMemberNo));
          friendRepository.delete(convertedEntity);
        }
        // 플친 요청 거절 -> friendEntity 삭제
        friendRepository.delete(onFriend);

        // 삭제된 후, 나의 플친 리스트 반환
        return friendRepository.findByToMemberAndStatus(toMember, FriendStatusType.PENDING);
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param userId
     * @param toMemberNo
     * @return 나의 플친 리스트
     * @Brief 상대방 차단. 상대방과 플친인 경우 또는 상대방이 요청 중인 경우 상대방에게서는 플친 삭제
     */
    public List<FriendEntity> blockFriend(Long fromMemberNo, Long toMemberNo) {

        MemberEntity fromMember = toEntityWithNo(fromMemberNo);

        // 나에게서 차단
        FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(fromMember, toEntityWithNo(toMemberNo));
        friendEntity.setStatus(FriendStatusType.BLOCK);

        // 상대방과의 관계가 존재하면서, 관계가 플친중 또는 요청대기인 경우, 상대방에게서는 친구 삭제
        FriendEntity convertedEntity = friendRepository.findByFromMemberAndToMember(toEntityWithNo(toMemberNo), fromMember);
        if(convertedEntity != null && convertedEntity.getStatus() != FriendStatusType.BLOCK) {
            friendRepository.delete(convertedEntity);
        }
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
            throw new RuntimeException("empty fromMember");
        }
        // toMember 부존재
        else if (friendEntity.getToMember() == null) {
            throw new RuntimeException("empty toMember");
        }
        // 이미 친구 또는 요청중일 경우 x -> 멤버의 조회 메서드 필요? duplication
        // 상대방이 친구 요청 중일 경우 x
        // fromMember와 toMember가 같을 경우 x
        // 차단 상태일 경우 x
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
