package city.olooe.plogging_project.security;


public interface Oauth2UserInfo {
  
  String getId();
  String getEmail();
  String getUserName();
  String getNickName();
  String getBirth();
  String getGender();
  String getProvider();
}
