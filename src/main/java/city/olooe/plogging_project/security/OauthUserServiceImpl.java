package city.olooe.plogging_project.security;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import city.olooe.plogging_project.model.AuthEntity;
import city.olooe.plogging_project.model.AuthType;
import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.model.PointHistoryEntity;
import city.olooe.plogging_project.model.RewardTypeStatus;
import city.olooe.plogging_project.persistence.AuthRepository;
import city.olooe.plogging_project.persistence.MemberRepository;
import city.olooe.plogging_project.service.PointHistoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 박연재
 * @date 23.06.xx
 * @brief OAuth2User 서비스 클래스
 */
@Service
@Slf4j
public class OauthUserServiceImpl extends DefaultOAuth2UserService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PointHistoryService pointHistoryService;

  @Autowired
  private AuthRepository authRepository;
  public OauthUserServiceImpl() {
    super();
  }

  /**
   * @author 박연재
   * @date 23.06.23
   * @brief OAuth2User 회원 불러옴
   */
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    final OAuth2User oAuth2User = super.loadUser(userRequest);
    try {
      log.info("Oauth2 회원 정보{}", new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    // final String username = (String) oAuth2User.getAttribute("login");
    final String authProvider = userRequest.getClientRegistration().getClientName();
    log.info("{}", authProvider);

    Oauth2UserInfo oauth2UserInfo = null;
    // 어트리뷰트에 있는 필요한 정보들을 추출해야 함.
    if (authProvider.equalsIgnoreCase("google")) {  // 구글
      log.info("구글 로그인 요청!");
      oauth2UserInfo = new GoogleOauthUser(oAuth2User.getAttributes());     
    } else if (authProvider.equalsIgnoreCase("kakao")) { // 카카오
      log.info("카카오 로그인 요청!");
      oauth2UserInfo = new KakaoOauthUser(oAuth2User.getAttributes());        
    } else if (authProvider.equalsIgnoreCase("naver")) { // 네이버
      log.info("네이버 로그인 요청!");
      oauth2UserInfo = new NaverOauthUser(oAuth2User.getAttributes());     
    }

    String id = oauth2UserInfo.getId();
    String provider = oauth2UserInfo.getProvider(); //google , naver, facebook etc
    String userName = provider + "_" + id;
    String nickName = oauth2UserInfo.getNickName();
    String birth = oauth2UserInfo.getBirth();
    String email = oauth2UserInfo.getEmail();
    String gender = oauth2UserInfo.getGender();


    MemberEntity memberEntity = null;
    AuthEntity authEntity = null;
    if (!memberRepository.existsByUserId(id)) {  
      // 해당 회원이 없으면 생성함
      memberEntity = MemberEntity.builder()
      .userId(id)
      .userName(userName)
      .nickName(nickName)
      .email(email)
      .gender(gender)
      .authProvider(authProvider).build();
      memberRepository.save(memberEntity);


      // 권한 등급
      authEntity = AuthEntity.builder()
      .authority(AuthType.ROLE_MEMBER)
      .memberNo(memberEntity)
      .build();

      memberEntity = memberRepository.findByUserName(userName);
      authRepository.save(authEntity);
      
      PointHistoryEntity historyEntity = PointHistoryEntity.builder()
          .memberNo(memberEntity)
          .type(RewardTypeStatus.SignUp)
          .point(RewardTypeStatus.SignUp.getValue())
          .build();
      pointHistoryService.createPoint(historyEntity);

    } else {
      // 이미 회원이 존재하면 기존 회원을 불러옴
      memberEntity = memberRepository.findByUserName(userName);
    }

    log.info("Successfully pulled user info username {} authProvider {}", id, authProvider);
    return new ApplicationUserPrincipal(id, memberEntity ,oAuth2User.getAttributes());
  }

}
