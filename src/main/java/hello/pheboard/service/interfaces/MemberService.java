package hello.pheboard.service.interfaces;

import hello.pheboard.dto.LoginDTO;
import hello.pheboard.dto.SignUpFormDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity signup(SignUpFormDto formDTO);

    ResponseEntity login(LoginDTO loginDTO);
}
