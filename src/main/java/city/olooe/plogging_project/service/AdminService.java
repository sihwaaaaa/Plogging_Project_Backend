package city.olooe.plogging_project.service;

import city.olooe.plogging_project.dto.MemberDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminService {

    private final MemberRepository memberRepository;

    public List<MemberEntity> getAll() {
        return memberRepository.findAll();
    }

}
