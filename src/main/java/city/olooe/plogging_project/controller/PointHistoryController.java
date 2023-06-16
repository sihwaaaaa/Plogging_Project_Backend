package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.*;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
@RequestMapping("history")
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService historyService;

    /*
     * @Author 이재원
     *
     * @Date 23.06.13
     *
     * @param pointNo
     *
     * @param type
     *
     * @return List<PointHistoryDTO>
     *
     * @Brief 포인트 번호의 유형별 조회
     */
    @GetMapping("/{pointNo}/{type}")
    public List<PointHistoryDTO> myPointNoList(@PathVariable Long pointNo, @PathVariable String type) {

        List<PointHistoryEntity> historyEntities = historyService.GetPointList(pointNo, type);

        return historyEntities.stream()
                .map(PointHistoryDTO::new)
                .collect(Collectors.toList());
    }

    /*
     * @Author 이재원
     *
     * @Date 23.06.13
     *
     * @param pointNo
     *
     * @param type
     *
     * @return List<PointHistoryDTO>
     *
     * @Brief 멤버 번호의 유형별 조회
     */

    @GetMapping("/{memberNo}/{type}")
    public List<PointHistoryDTO> myMemberNoList(@PathVariable MemberEntity memberNo, @PathVariable String type) {

        List<PointHistoryEntity> historyEntities = historyService.GetMemberList(memberNo, type);

        return historyEntities.stream()
                .map(PointHistoryDTO::new)
                .collect(Collectors.toList());
    }

    /*
     * @Author 이재원
     *
     * @Date 23.06.13
     *
     * @param MemberNo
     *
     * @return Long(CurrentPoint, TotalPoint)
     *
     * @Brief 멤버 번호를 통해 현재 포인트와 누적 포인트를 출력
     */
    @GetMapping("{memberNo}")
    public Long myMemberNoTotalPoint(@PathVariable MemberEntity memberNo) {
        return historyService.findBySumPoint(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
    }

    @GetMapping("/list/{memberNo}")
    public ResponseEntity<?> myMemberNoCurrentPoint(@PathVariable MemberEntity memberNo) {
        List<PointHistoryEntity> historyEntities = historyService.test_memberNoList(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
        List<PointHistoryDTO> historyDTOS = historyEntities.stream()
                .map(PointHistoryDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(historyDTOS).build());
    }

    //    @GetMapping("/status/{status}")
//    public ResponseEntity<?> myStatusList(@PathVariable boolean status) {
//        List<PointHistoryEntity> historyEntities = historyService.GetfindByStatus(status);
//        List<PointHistoryDTO> historyDTOS = historyEntities.stream()
//                .map(PointHistoryDTO::new)
//                .collect(toList());
//        return ResponseEntity.ok().body(ResponseDTO.builder().data(historyDTOS).build());
//    }
    @GetMapping("/status/{status}")
    public List<PointHistoryEntity> getstatus(@PathVariable boolean status) {
        if (!status) {// status의 값이 기본값(false:0)이 아니면
            return historyService.GetfindByStatus(true); // true 리턴
        }
        return historyService.GetfindByStatus(false); // 아니라면 false 리턴
    }

    @PostMapping("/donation")
    public ResponseEntity<?> registerDonation(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody PointHistoryDTO dto) {
        log.info(String.valueOf(dto));

        PointHistoryEntity historyEntity = PointHistoryEntity.builder()
                .memberNo(MemberEntity.builder().memberNo(user.getMember().getMemberNo()).build())
                .point(dto.getPoint())
                .type(RewardTypeStatus.Donation)
                .rewardNo(RewardEntity.builder().rewardNo(dto.getRewardNo()).build())
                .build();

        PointHistoryEntity createDonation = historyService.createPoint(historyEntity);

        PointHistoryDTO historyDTO = new PointHistoryDTO(createDonation);

        return ResponseEntity.ok().body(historyDTO);
    }
}