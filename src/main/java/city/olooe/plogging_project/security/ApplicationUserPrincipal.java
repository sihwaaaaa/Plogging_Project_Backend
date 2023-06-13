package city.olooe.plogging_project.security;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
public class ApplicationUserPrincipal implements UserDetails {

    private final MemberEntity member;

    public ApplicationUserPrincipal(MemberEntity member) {
       this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthEntities().stream()
                .map(member -> new SimpleGrantedAuthority(member.getAuthority().getKey())).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }
    /**
     * PK값 삽입
     * @return String
     */
    @Override
    public String getUsername() {
        return String.valueOf(member.getUserId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserPrincipal that = (ApplicationUserPrincipal) o;
        return member.equals(that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member);
    }
}

