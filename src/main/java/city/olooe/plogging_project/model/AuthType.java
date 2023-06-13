package city.olooe.plogging_project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthType {
  ROLE_MEMBER("ROLE_MEMBER", "일반 회원"),
  ROLE_ADMIN("ROLE_ADMIN", "관리자");

  @Getter
  private final String key;
  @Getter
  private final String value;
}
