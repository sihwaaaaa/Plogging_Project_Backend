package city.olooe.plogging_project.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private static final String LOCAL_REDIRECT_URL = "http://localhost:3000/";

  @Autowired
  private MemberRepository repository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    TokenProvider tokenProvider = new TokenProvider();
    String userId = ((ApplicationOAuth2User) authentication.getPrincipal()).getName();
    String token = tokenProvider.create(authentication);
    String username = repository.findByUserId(userId).toString();

    Optional<Cookie> oCookie = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(RedirectUrlCookieFilter.REDIRECT_URI_PARAM)).findFirst();
    Optional<String> redirectUri = oCookie.map(Cookie::getValue);

    log.info("token {}", token);
    log.info("username {}", username);
    // response.getWriter().write(token);
    // response.sendRedirect("http://localhost:3000/socialLogin?token=" + token);
    response.sendRedirect(
        redirectUri.orElseGet(() -> LOCAL_REDIRECT_URL) + "socialLogin?token=" + token + "&username=" + username);

  }
}
