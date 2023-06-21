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
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService historyService;


    /**
     * @Author 이재원
     * @Date 23.06.13
     * @param memberNo
     * @return Long(CurrentPoint, TotalPoint)
     * @Brief 멤버 번호를 통해 누적 포인트를 출력
     */
    @GetMapping("/total/{memberNo}")
    public Long myMemberNoTotalPoint(@PathVariable MemberEntity memberNo) {
        return historyService.findBySumPoint(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
    }
    /**
     * @Author 이재원
     * @Date 23.06.13
     * @param memberNo
     * @return List<ResponseDTO>
     * @Brief 조회한 멤버 번호의 포인트 적립 내역
     */
    @GetMapping("/list/{memberNo}")

    public ResponseEntity<?> myMemberNoCurrentPoint(@PathVariable MemberEntity memberNo) {
        List<PointHistoryEntity> historyEntities = historyService.test_memberNoList(MemberEntity.builder().memberNo(memberNo.getMemberNo()).build());
        List<PointHistoryDTO> historyDTOS = historyEntities.stream()
                .map(PointHistoryDTO::new)
                .collect(toList());
        return ResponseEntity.ok().body(ResponseDTO.builder().data(historyDTOS).build());
    }

    @PostMapping("/Donation")
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

    /**
     * @Author 이재원
     * @Date 23.06.19
     * @param user
     * @param dto
     * @return historyDTO
     * @Brief 랜덤박스 신청 기능 메서드
     */
    @PostMapping("/Product")
    public ResponseEntity<?> registerProduct(@AuthenticationPrincipal ApplicationUserPrincipal user, @RequestBody PointHistoryDTO dto) {

        PointHistoryEntity historyEntity = PointHistoryEntity.builder()
                .memberNo(MemberEntity.builder().memberNo(user.getMember().getMemberNo()).build())
                .point(dto.getPoint())
                .type(RewardTypeStatus.Product)
                .rewardNo(RewardEntity.builder().rewardNo(dto.getRewardNo()).build())
                .build();

        PointHistoryEntity createDonation = historyService.createPoint(historyEntity);

        PointHistoryDTO historyDTO = new PointHistoryDTO(createDonation);

        return ResponseEntity.ok().body(historyDTO);
    }

    /**
     * @Author 이재원
     * @Date 23.06.19
     * @param
     * @return historyDTO
     * @Brief 각 회원의 rank list
     */
    @GetMapping("/list/rank")
    public ResponseEntity<?> getRank() {
        return ResponseEntity.ok().body(historyService.getRankList());
    }

    /**
     * @Author 이재원
     * @Date 23.06.19
     * @param memberNo
     * @return historyDTO
     * @Brief 회원의 누적 포인트와 등급
     */
    @GetMapping("/rank/badge/{memberNo}")
    public ResponseEntity<?> getBadge(@PathVariable Long memberNo) {
        return ResponseEntity.ok().body(historyService.getBadge(memberNo));
    }
}
