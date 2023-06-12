package city.olooe.plogging_project.security;

import city.olooe.plogging_project.dto.AuthDTO;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 박연재
 * @date 2023.06.11
 * @brief userPrincipal의 서비스를 담당
 */
@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("그러한 회원은 존재하지 않습니다 : " + userName));
        return new ApplicationUserPrincipal(member);
    }

    @Transactional
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("그러한 회원은 존재하지 않습니다 : " + userId));
        return new ApplicationUserPrincipal(member);
    }
}
