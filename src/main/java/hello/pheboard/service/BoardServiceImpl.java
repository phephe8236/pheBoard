package hello.pheboard.service;

import hello.pheboard.domain.Board;
import hello.pheboard.domain.Member;
import hello.pheboard.domain.MemberRole;
import hello.pheboard.dto.DetailDTO;
import hello.pheboard.dto.ListDTO;
import hello.pheboard.dto.PostFormDTO;
import hello.pheboard.repository.BoardRepository;
import hello.pheboard.repository.MemberRepository;
import hello.pheboard.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity save(PostFormDTO formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getMemberId());
        if(member.isPresent()){
            Member memberEntity = member.get();

            Board post = Board.builder()
                    .title(formDTO.getTitle())
                    .content(formDTO.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .adminViews(0)
                    .userViews(0)
                    .likes(0)
                    .member(memberEntity)
                    .build();

            boardRepository.save(post);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public List<ListDTO> getAll() {

        List<Board> posts = boardRepository.findAll();  // Repository에서 게시글을 다 가져온다.
        List<ListDTO> list = new ArrayList<>();         // DTO를 담을 리스트를 만들어준다.

        for (Board post : posts) {
            Member member = post.getMember();

            ListDTO dto = ListDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .userViews(post.getUserViews())
                    .adminViews(post.getAdminViews())
                    .memberName(member.getName())
                    .build();

            list.add(dto);
        }
        return list;
    }

    @Override
    public DetailDTO getDetail(Long id, String memberId) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        Member member = boardEntity.getMember();

        if(!memberId.equals(member.getId())) {
            if (member.getRole().equals(MemberRole.ADMIN)) {
                boardEntity.countAdmin();
            }else {
                boardEntity.countUser();
            }
        }

        DetailDTO detailDTO = DetailDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .userViews(boardEntity.getUserViews())
                .adminViews(boardEntity.getAdminViews())
                .memberId(member.getId())
                .memberName(member.getName())
                .build();

        return detailDTO;
    }

    @Override
    public ResponseEntity remove(Long id) {

        boardRepository.deleteById(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }


}
