package city.olooe.plogging_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : 김성진
 * 
 * @date : 2023.06.02
 * 
 * @brief : 게시글 관련 controller
 */

@Controller
public class BoardController {
  @GetMapping("/board")
  public String list() {
    return "hello"; // 차후 경로 지정
  }

  @GetMapping("/post")
  public String post() {
    return "world"; // 차후 경로 지정
  }
}
