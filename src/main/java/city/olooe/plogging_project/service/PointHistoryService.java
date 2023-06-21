package city.olooe.plogging_project.service;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.persistence.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;
    private final MemberRepository memberRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param type(유형)
     * @return List<PointHistoryEntity>
     * @Author 이재원
     * @Date 23.06.12     *
     * @Brief 포인트 유형별 조회
     */
    @Transactional
    public List<PointHistoryEntity> GetPointList(Long pointNo, String type) {
        return pointHistoryRepository.findByPointNoAndType(pointNo, RewardTypeStatus.valueOf(type));
    }

    /**
     * @param memberNo
     * @param type(유형)
     * @return List<PointHistoryEntity>
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 멤버 번호를 조회하여 포인트 유형 조회
     */
    @Transactional
    public List<PointHistoryEntity> GetMemberList(MemberEntity memberNo, String type) {
        return pointHistoryRepository.findByTypeAndMemberNo(RewardTypeStatus.valueOf(type), memberNo);
    }

    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 적립
     */
    @Transactional
    public PointHistoryEntity createPoint(final PointHistoryEntity entity) {
        return pointHistoryRepository.save(entity);
    }

    /**
     * @param
     * @return PointNo
     * @Author 이재원
     * @Date 23.06.12
     * @Brief 포인트 유형 조회
     */
    @Transactional
    public List<PointHistoryEntity> findByTypeList(String type) {
        return pointHistoryRepository.findByType(RewardTypeStatus.valueOf(type));
    }

    /*
     * @param MemberNo
     * @return currentPoint
     * @Author 이재원
     * @Date 23.06.14
     * @Brief 회원의 누적 포인트 조회
     */
    @Transactional
    public Long findByTotalPoint() {
        return pointHistoryRepository.totalPoint();
    }

    @Transactional
    public Long findBySumPoint(MemberEntity memberNo) {
        return pointHistoryRepository.sumPoint(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
    }

    @Transactional
    public List<PointHistoryEntity> test_memberNoList(MemberEntity memberNo) {
        return pointHistoryRepository.findByMemberNo(MemberEntity.builder().memberNo(1L).build());
    }

    @Transactional
    public List<PointHistoryEntity> GetfindByStatus(boolean status) {
        return pointHistoryRepository.findByStatus(status);
    }

    @Transactional
    public List<MemberEntity> GetMemberList(MemberEntity member) {
        return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "memberNo"));
    }

    @Transactional
    public Long GetMemberSumPoint(MemberEntity memberNo) {
        return pointHistoryRepository.sumPoint(memberNo);
    }



//    @Transactional
//    public List<Map<String, Object>> getRankList() {
//        return jdbcTemplate.query("select tp.memberNo, sum(tp.point) pt, \n" +
//                "RANK() OVER (ORDER BY sum(tp.point) DESC) RANK2\n" +
//                "from tbl_pointhistory tp \n" +
//                "group by tp.memberNo\n" +
//                "order by 2 desc limit 5", (rs, rowNum) -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("memberNo", rs.getString(1));
//            map.put("point", rs.getString(2));
//            map.put("rank", rs.getString(3));
//            return map;
//        });
//    }

    @Transactional
    public List<Map<String, Object>> getRankList() {
        return jdbcTemplate.query("SELECT\n" +
                "  rank() OVER (ORDER BY subquery.totalpoint DESC) AS rank,  \n" +
                "  tm.userId,\n" +
                "  subquery.totalpoint,\n" +
                "  subquery.badgeName\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    memberNo,\n" +
                "    pt AS totalpoint,\n" +
                "    NVL(badgeNo, 1) AS badgeNo,\n" +
                "    NVL(name, '씨앗') AS badgeName\n" +
                "  FROM (\n" +
                "    SELECT\n" +
                "      memberNo,\n" +
                "      SUM(point) AS pt\n" +
                "    FROM\n" +
                "      tbl_pointhistory\n" +
                "    GROUP BY\n" +
                "      memberNo\n" +
                "  ) a\n" +
                "  LEFT JOIN tbl_badge tb ON pt BETWEEN tb.minPoint AND tb.point\n" +
                ") subquery\n" +
                "LEFT JOIN tbl_badge tb2 ON subquery.totalpoint BETWEEN tb2.minPoint AND tb2.point\n" +
                "left join tbl_member tm on subquery.memberNo = tm.memberNo\n" +
                "ORDER by rank\n" +
                "LIMIT 5", (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("rank", rs.getString(1));
            map.put("userId", rs.getString(2));
            map.put("totalpoint", rs.getString(3));
            map.put("badgename", rs.getString(4));
            return map;
        });
    }

    @Transactional
    public Map<String, Object> getBadge(Long memberNo) {
        return jdbcTemplate.queryForObject("select memberNo, pt, nvl(badgeNo, 1) badgeNo, nvl(name, '씨앗') bagdeName from (\n" +
                "\tselect memberNo, sum(point) pt\n" +
                "\tfrom tbl_pointhistory tp \n" +
                "\tgroup by memberNo\n" +
                ")a\n" +
                "left join tbl_badge tb on pt between minPoint and point\n" +
                "where memberNo =" + memberNo, (rs, rn) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("memberNo", rs.getString(1));
            map.put("point", rs.getString(2));
            map.put("badgeNo", rs.getString(3));
            map.put("badgeName", rs.getString(4));
            return map;
        });
    }
}


