package hello.pheboard.service.interfaces;

import hello.pheboard.dto.PostFormDTO;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity save(PostFormDTO formDTO);
}
