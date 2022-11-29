package hello.pheboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//JSON 반환
@RestController //REST 방식의 응답을 처리하는 컨트롤러를 구현할 수 있다.
@RequestMapping("/api") //메소드 뿐만 아니라 클래스 단위에도 해당 어노테이션을 사용할 수 있다.
public class ApiController {

    @GetMapping("/name")
    public String name() {
        return "박형은";
    }
}
