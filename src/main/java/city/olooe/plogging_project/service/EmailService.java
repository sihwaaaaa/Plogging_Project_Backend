package city.olooe.plogging_project.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

  private static final String LOCAL_REDIRECT_URL = "http://localhost:3000/"; // 프론트 엔드 경로

  @Autowired
  private JavaMailSender emailSender; // 이메일 전송자

  public String ePw = ""; // 인증 코드

  private MimeMessage createMessage(String to, String userName, Long memberNo, String userId)
      throws MessagingException, UnsupportedEncodingException {
    log.info("{}", to);
    log.info("{}", ePw);

    String setFrom = "ploggingmanager@gmail.com";
    MimeMessage message = emailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, to);
    message.setSubject("테스트 중", "utf-8");

    // epw => 인증코드
    String msg = "";
    if (userName == null) {
      msg += messageContent(msg, ePw); // 이메일 인증 컨텐츠
    } else {
      msg += linkContent(msg, memberNo, userId);
    }
    message.setText(msg, "utf-8", "html");
    message.setFrom(new InternetAddress(setFrom, "Plogging"));
    return message;
  }

  public String createKey() {
    StringBuffer key = new StringBuffer();
    Random rnd = new Random();

    for (int i = 0; i < 8; i++) { // 인증코드 8자리
      int index = rnd.nextInt(3); // 0~2 까지 랜덤

      switch (index) {
        case 0:
          key.append((char) ((int) (rnd.nextInt(26)) + 97));
          // a~z (ex. 1+97=98 => (char)98 = 'b')
          break;
        case 1:
          key.append((char) ((int) (rnd.nextInt(26)) + 65));
          // A~Z
          break;
        case 2:
          key.append((rnd.nextInt(10)));
          // 0~9
          break;
      }

    }
    return key.toString();
  }

  private String introContent(String msg) {
    msg += "<p> 줍깅 사이트에 오신 것을 환영합니다.</p>";
    msg += "<p> 아래 코드를 입력하세요.</p>";
    msg += "<p> 감사합니다.</p>";
    return msg;
  }

  public String messageContent(String msg, String epw) {
    msg += "<div>";
    msg += introContent(msg);
    msg += "</br>";
    msg += "<h2>회원가입 인증 코드입니다.</h2>";
    msg += "<div>";
    msg += "코드 번호 : <strong>";
    msg += ePw + "</strong></div></br>";
    msg += "</div>";
    return msg;
  }

  public String linkContent(String msg, Long memberNo, String userId) {
    msg += "<div>";
    msg += introContent(msg);
    msg += "</br>";
    msg += "'<p>아래 링크를 통해서 본인 아이디를 확인해주세요</p>";
    msg += "<a href='" + LOCAL_REDIRECT_URL + "member/findId/" + memberNo + "'>";
    msg += "회원 아이디 확인하러 가기";
    msg += "</a></div>";
    return msg;
  }

  public String sendMessage(String to, String userName, Long memberNo, String userId)
      throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = null;
    if (userName == null) {
      ePw = createKey();
      message = createMessage(to, null, null, null); // 수신자를 포함한 message를 생성
      emailSender.send(message);
      return ePw;
    } else {
      message = createMessage(to, userName, memberNo, userId); // userName, 수신자를 포함한 message를 생성
      emailSender.send(message);
      return "이메일이 발송되었습니다.";
    }

  }
}
