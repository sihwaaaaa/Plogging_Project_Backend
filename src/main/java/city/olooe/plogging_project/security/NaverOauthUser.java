package city.olooe.plogging_project.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import city.olooe.plogging_project.model.MemberEntity;

public class NaverOauthUser implements OAuth2User, Oauth2UserInfo{

  private Map<String, Object> attributes;
  private Map<String, Object> attributeName;
  private Collection<? extends GrantedAuthority> authorities;

  public NaverOauthUser(Map<String, Object> attributes){
    this.attributes = attributes;
    this.attributeName =(Map<String, Object>)attributes.get("response");
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getName() {
    return (String)attributeName.get("name");
  }

  @Override
  public <A> A getAttribute(String name) {
    return OAuth2User.super.getAttribute(name);
  }

  @Override
  public String getBirth() {
    // TODO Auto-generated method stub
    return (String)attributeName.get("birth");
  }

  @Override
  public String getEmail() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getGender() {
    if(((String)attributeName.get("gender")).equals("M")){
      return "남자";
    }
    return "여자";
  }

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return (String)attributeName.get("id");
  }

  @Override
  public String getNickName() {
    // TODO Auto-generated method stub
    return (String)attributeName.get("nickname");
  }

  @Override
  public String getProvider() {
    // TODO Auto-generated method stub
    return "naver";
  }

  @Override
  public String getUserName() {
    // TODO Auto-generated method stub
    return null;
  }

  
}
