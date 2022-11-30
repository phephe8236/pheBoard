package hello.pheboard.controller;

import hello.pheboard.dto.SignUpFormDto;
import hello.pheboard.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//JSON 반환
@RestController            //REST 방식의 응답을 처리하는 컨트롤러를 구현할 수 있다.
@RequestMapping("/api") //메소드 뿐만 아니라 클래스 단위에도 해당 어노테이션을 사용할 수 있다.
@RequiredArgsConstructor //final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해줌, 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 수정할 필요가 없음.
public class ApiController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDto formDTO) {
        return memberService.signup(formDTO);
    }

    /*
        @RequestBody : HTTP 요청의 body(JSON이나 XML 등)를 자바 객체로 변환시켜줌
     */
    
    @GetMapping("/name")
    public String name() {
        return "park";
    }
}
