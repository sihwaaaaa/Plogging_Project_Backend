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
        MemberEntity member = memberRepository.findByUserName(userName);
        return new ApplicationUserPrincipal(member);
    }

    @Transactional
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUserId(userId);
        if (member == null) {
            throw new UsernameNotFoundException("해당 유저가 존재하지 않는다.");
        }
        return new ApplicationUserPrincipal(member);
    }
}
