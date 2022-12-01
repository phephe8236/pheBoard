package hello.pheboard.controller;

import hello.pheboard.dto.DetailDTO;
import hello.pheboard.dto.ListDTO;
import hello.pheboard.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardServiceImpl boardService;

    @GetMapping("/hello")
    public String hello(Model model) { //model 객체에 data를 담을 수 있다.
        model.addAttribute("name", "phephe");  //key,data
        model.addAttribute("img", "image/foxdo.png");
        return "hello";
    }

    //home에서 게시글 전체조회
    @GetMapping("/")
    public String index(Model model) {
        List<ListDTO> posts = boardService.getAll();    //Service에서 받아온 dto 리스트를 Model에 담아 넘겨줌.
        model.addAttribute("posts", posts);
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

    /**
     * 게시글 상세 조회
     * Service단에서 반환한 dto를 Model 객체에 담는다
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, @CookieValue("id") String memberId) {
        //@PathVariable : 주소의 일부분을 변수로 사용하고 싶을 때, 사용시 null이나 공백이 들어가면 안됨
        DetailDTO post = boardService.getDetail(id, memberId);
        model.addAttribute("post", post);

        return "detail";
    }
}
