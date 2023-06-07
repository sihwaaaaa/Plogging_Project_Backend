package city.olooe.plogging_project.service;

import city.olooe.plogging_project.model.FriendEntity;
import city.olooe.plogging_project.model.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.FriendRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 천은경
 * @date: 23.06.05
 * @brief: Friend 테이블 서비스 로직
 */

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;



    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param fromMemberNo
     * @param toMemberNo
     * @return FriendEntity
     * @Brief 플친 신청
     */
    public FriendEntity requestFriend(Long fromMemberNo, Long toMemberNo) {
        FriendEntity friendEntity = new FriendEntity(toEntity(fromMemberNo), toEntity(toMemberNo));
        // 유효성 검사
        validate(friendEntity);
        // 저장 및 반환
        return friendRepository.save(friendEntity);
    }

    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param memberNo
     * @param status
     * @return List<FriendEntity>
     * @Brief 나의 팔로잉 상태별 플친 리스트 조회 : 요청대기 / 플친중 / 차단
     */
    public List<FriendEntity> GetMyFriendList(Long memberNo, String status) {
        return friendRepository.findByFromMemberAndStatus(toEntity(memberNo), FriendStatusType.valueOf(status));
    }

    /**
     * @Author 천은경
     * @Date 23.06.06
     * @param memberNo
     * @param status
     * @return List<FriendEntity>
     * @Brief 나의 팔로워 상태별 플친 리스트 조회 : 요청대기 / 플친중 / 차단
     */
    public List<FriendEntity> getFriendListToMe(Long memberNo, String status){
        return friendRepository.findByToMemberAndStatus(toEntity(memberNo), FriendStatusType.valueOf(status));
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param fromMemberNo
     * @param toMemberNo
     * @Brief 플친 요청 수락
     */
    public void acceptFriendRequest(Long fromMemberNo, Long toMemberNo) {
       FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(toEntity(fromMemberNo), toEntity(toMemberNo));
       friendEntity.setFriend();
    }


    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param fromMemberNo
     * @param toMemberNo
     * @Brief 플친 요청 거절 및 플친 삭제
     */
    public void removeFriend(Long fromMemberNo, Long toMemberNo) {
        FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(toEntity(fromMemberNo), toEntity(toMemberNo));

        // 이미 플친인 경우는 플친 삭제이기 때문에, fromMember와 toMember가 교차된 FriendEntity도 삭제
        if(friendEntity.getStatus().equals(FriendStatusType.FRIEND)){
          FriendEntity convertedEntity = friendRepository.findByFromMemberAndToMember(toEntity(toMemberNo), toEntity(fromMemberNo));
          friendRepository.delete(convertedEntity);
        }

        friendRepository.delete(friendEntity);
    }

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param fromMemberNo
     * @param toMemberNo
     * @Brief 상대방 차단. 상대방과 플친인 경우 또는 상대방이 요청 중인 경우 상대방에게서는 플친 삭제
     */
    public void blockFriend(Long fromMemberNo, Long toMemberNo) {
        // 나에게서 차단
        FriendEntity friendEntity = friendRepository.findByFromMemberAndToMember(toEntity(fromMemberNo), toEntity(toMemberNo));
        friendEntity.setStatus(FriendStatusType.BLOCK);

        // 상대방과의 관계가 존재하면서, 관계가 플친중 또는 요청대기인 경우, 상대방에게서는 친구 삭제
        FriendEntity convertedEntity = friendRepository.findByFromMemberAndToMember(toEntity(toMemberNo), toEntity(fromMemberNo));
        if(convertedEntity != null && convertedEntity.getStatus() != FriendStatusType.BLOCK) {
            friendRepository.delete(convertedEntity);
        }
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
        // 이미 친구일 경우 x -> 멤버의 조회 메서드 필요? duplication
        // 일방이 친구 요청 중일 경우 x
    }

    /**
     * @Authoer 천은경
     * @Date 23.06.07
     * @param memberNo
     * @return MemberEntity
     * @Brief memberNo로 MemberEntity를 반환하는 메서드
     */
    private MemberEntity toEntity(Long memberNo) {
        return MemberEntity.builder().memberNo(memberNo).build();
    }

}
