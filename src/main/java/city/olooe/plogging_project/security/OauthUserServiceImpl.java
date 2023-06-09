package city.olooe.plogging_project.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class OauthUserServiceImpl extends DefaultOAuth2UserService{

  @Autowired
  private MemberRepository memberRepository;
  

  public OauthUserServiceImpl() {
    super();
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    final OAuth2User oAuth2User = super.loadUser(userRequest);
    try {
      log.info("Oauth2 User Info{}", new ObjectMapper().writeValueAsString(oAuth2User));
    } catch (Exception e) {
      e.printStackTrace();
    }
    // final String username = (String) oAuth2User.getAttribute("login");
    final String authProvider = userRequest.getClientRegistration().getClientName();


    log.info("{}", authProvider);

    String userId = null;
    if (authProvider.equalsIgnoreCase("github")) {
      userId = (String) oAuth2User.getAttribute("login");
    } else if (authProvider.equalsIgnoreCase("google")) {
      userId = (String) oAuth2User.getAttribute("email");
    } else if (authProvider.equalsIgnoreCase("kakao")) {
      Map<String, String> map =  oAuth2User.getAttribute("kakao_account");
      userId = map.get("email");
      log.info(userId);
    } 

    MemberEntity memberEntity = null;
    if (!memberRepository.existsByUserId(userId)) {
      memberEntity = memberEntity.builder().userId(userId).authProvider(authProvider).build();

      memberEntity = memberRepository.save(memberEntity);
    } else {
      memberEntity = memberRepository.findByUserId(userId);
    }

    log.info("Successfully pulled user info username {} authProvider {}", userId, authProvider);
    return new ApplicationOAuth2User(memberEntity.getUserId(), oAuth2User.getAttributes());
  }
  
}
