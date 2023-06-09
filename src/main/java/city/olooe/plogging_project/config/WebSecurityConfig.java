package city.olooe.plogging_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.filter.CorsFilter;

import city.olooe.plogging_project.security.JwtAuthenticationFilter;
import city.olooe.plogging_project.security.OAuthSuccessHandler;
import city.olooe.plogging_project.security.OauthUserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 박연재
 * 
 * @date: 2023.06.04
 * 
 * @brief: 토큰 기반의 인증 웹 시큐리틔 컨피그
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private OauthUserServiceImpl oauthUserServiceImpl;

  @Autowired
  private OAuthSuccessHandler oAuthSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/", "/member/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login().authorizationEndpoint().baseUri("/oauth2/member")
        .and()
        .redirectionEndpoint()
        .baseUri("/oauth2/callback")
        .and()
        .userInfoEndpoint()
        .userService(oauthUserServiceImpl)
        .and()
        .successHandler(oAuthSuccessHandler)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

    http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
