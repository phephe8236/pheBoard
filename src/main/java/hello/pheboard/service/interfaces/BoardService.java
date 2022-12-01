package hello.pheboard.service.interfaces;

import hello.pheboard.dto.DetailDTO;
import hello.pheboard.dto.ListDTO;
import hello.pheboard.dto.PostFormDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {

    ResponseEntity save(PostFormDTO formDTO);

    //게시글 전체 조회
    List<ListDTO> getAll();

    //게시글 상세조회
    DetailDTO getDetail(Long id, String memberId);

    //게시글 삭제
    ResponseEntity remove(Long id);
}
