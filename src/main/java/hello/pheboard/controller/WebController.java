package hello.pheboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/hello")
    public String hello(Model model) { //model 객체에 data를 담을 수 있다.
        model.addAttribute("name", "phephe");  //key,data
        model.addAttribute("img", "image/foxdo.png");
        return "hello";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    /**
     * 회원가입, 로그인, 로그아웃
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    /**
     * 게시글 등록
     */
    @GetMapping("/new")
    public String newPost() {
        return "new";
    }
}
