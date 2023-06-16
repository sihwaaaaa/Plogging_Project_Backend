package city.olooe.plogging_project.controller;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.dto.ResponseDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.security.ApplicationUserPrincipal;
import city.olooe.plogging_project.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @GetMapping("member")
    public ResponseEntity<?> getMembers(@AuthenticationPrincipal ApplicationUserPrincipal user) {
        List<MemberEntity> members = adminService.getAll();

        List<MemberDTO> memberDTOS = members.stream()
                .map(member -> MemberDTO.builder()
                        .memberNo(member.getMemberNo())
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .regDate(member.getRegDate())
                        .birth(member.getBirth())
                        .gender(member.getGender())
                        .authProvider(member.getAuthProvider())
                        .authList(member.getAuthEntities().stream()
                                .map(auth -> auth.getAuthority().getKey())
                                .collect(toList()))
                        .build())
                .collect(toList());

        return ResponseEntity.ok().body(ResponseDTO.builder().data(memberDTOS).build());
    }
}
