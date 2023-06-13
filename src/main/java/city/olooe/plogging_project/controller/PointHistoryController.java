package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.PointHistoryDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService historyService;

    /**
     * @Author 이재원
     * @Date 23.06.13
     * @param pointNo
     * @param type
     * @return List<PointHistoryDTO>
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
      @Author 이재원
     * @Date 23.06.13
     * @param pointNo
     * @param type
     * @return List<PointHistoryDTO>
     * @Brief 멤버 번호의 유형별 조회
     */

    @GetMapping("/{memberNo}/{type}")
    public List<PointHistoryDTO> myMemberNoList(@PathVariable MemberEntity memberNo, @PathVariable String type) {

        List<PointHistoryEntity> historyEntities = historyService.GetMemberList(memberNo, type);

        return historyEntities.stream()
                .map(PointHistoryDTO::new)
                .collect(Collectors.toList());
    }
}
