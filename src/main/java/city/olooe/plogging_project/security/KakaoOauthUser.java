package city.olooe.plogging_project.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import city.olooe.plogging_project.model.MemberEntity;
import lombok.Getter;

// 닉네임, 이름, 성별, 생일
@Getter
public class KakaoOauthUser implements OAuth2User, Oauth2UserInfo{

  // 카카오 어트리뷰트 내에 들어있는 정보들
  private Map<String, Object> attributes;
  private Map<String, Object> attributesProperties; // getAttributes
  private Map<String, Object> attributesAccount; // getAttributes
  private Map<String, Object> attributesProfile; // getAttributes

  private Collection<? extends GrantedAuthority> authorities;


  public KakaoOauthUser(Map<String, Object> attributes){
    this.attributes = attributes;
    this.attributesProperties = (Map<String, Object>)attributes.get("properties");
    this.attributesAccount = (Map<String, Object>)attributes.get("kakao_account");
    this.attributesProfile = (Map<String, Object>)attributesAccount.get("profile");

    this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"));
  }

  @Override
  public String getProvider(){
    return "kakao";
  }
  @Override
  public String getId(){
    return String.valueOf(attributes.get("id"));
  }
  @Override
  public String getEmail() {
    return (String)attributesAccount.get("email");
  }
  @Override
  public String getBirth() {
    return (String)attributesAccount.get("birthday");
  }
  @Override
  public String getGender() {
    if(((String)attributesAccount.get("gender")).equals("male")){
      return "남자";
    }
    return "여자";
  }
  @Override
  public String getUserName() {
    return null;
  }
  @Override
  public String getNickName() {
    return (String)attributesProperties.get("nickname");
  }

  @Override
  public String getName() {
    return String.valueOf(attributes.get("id"));
  }  

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  

}
