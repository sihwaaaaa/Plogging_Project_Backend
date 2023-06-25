package city.olooe.plogging_project.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import city.olooe.plogging_project.model.MemberEntity;

public class GoogleOauthUser implements OAuth2User, Oauth2UserInfo{

  private Map<String, Object> attributes;
  private Collection<? extends GrantedAuthority> authorities;


  public GoogleOauthUser(Map<String, Object> attributes){
    this.attributes = attributes;
  }

  @Override
  public String getBirth() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getEmail() {
    // TODO Auto-generated method stub
    return (String)attributes.get("email");
  }

  @Override
  public String getGender() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return String.valueOf(attributes.get("sub"));
  }

  @Override
  public String getNickName() {
    // TODO Auto-generated method stub
    return (String)attributes.get("name");
  }

  @Override
  public String getProvider() {
    // TODO Auto-generated method stub
    return "google";
  }

  @Override
  public String getUserName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Object> getAttributes() {
    // TODO Auto-generated method stub
    return this.attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

}
