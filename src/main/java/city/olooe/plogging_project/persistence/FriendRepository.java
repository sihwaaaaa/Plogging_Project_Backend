package city.olooe.plogging_project.persistence;

import city.olooe.plogging_project.model.friend.FriendEntity;
import city.olooe.plogging_project.model.friend.FriendStatusType;
import city.olooe.plogging_project.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * @author : 천은경
 * @date: 23.06.02
 * @brief: FriendEntity jpa 구현체
 */
@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    /**
     * @author 천은경
     * @date 23.06.06
     * @param friendNo
     * @return FriendEntity
     * @Brief 플친번호(PK)로 플친 단일 조회
     */
    FriendEntity findByFriendNo(Long friendNo);

    /**
     * @Author 천은경
     * @Date 23.06.07
     * @param fromMember
     * @param toMember
     * @return FriendEntity
     * @Brief 나와 상대의 플친 단일 조회
     */
    FriendEntity findByFromMemberAndToMember(MemberEntity fromMember, MemberEntity toMember);

    @Query("select f.status from FriendEntity f where f.fromMember = :fromMember and f.toMember = :toMember")
    Optional<FriendStatusType> findStatusBy(MemberEntity fromMember, MemberEntity toMember);

    /**
     * @author 천은경
     * @date 23.06.06
     * @param fromMember
     * @return List<FriendEntity>
     * @Brief 나의 플친 리스트 상태별 조회 : 요청대기상태, 플친상태, 차단상태
     */
    List<FriendEntity> findByFromMemberAndStatus(MemberEntity fromMember, FriendStatusType status);

    /**
     * @author 천은경
     * @date 23.06.06
     * @param fromMember
     * @return List<FriendEntity>
     * @Brief 나를 대상으로하는 플친 리스트 상태별 조회 : 요청대기상태, 플친상태, 차단상태
     */
    List<FriendEntity> findByToMemberAndStatus(MemberEntity toMember, FriendStatusType status);

    /**
     * @Author 천은경
     * @Date 23.06.11
     * @Breif 모든 플친 리스트 (상태 무관)
     */
    List<FriendEntity> findAll();

}
