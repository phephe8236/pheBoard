package hello.pheboard.service;

import hello.pheboard.domain.Member;
import hello.pheboard.domain.MemberRole;
import hello.pheboard.dto.LoginDTO;
import hello.pheboard.dto.SignUpFormDto;
import hello.pheboard.repository.MemberRepository;
import hello.pheboard.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity signup(SignUpFormDto formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getId());

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .id(formDTO.getId())
                    .password(formDTO.getPassword())
                    .name(formDTO.getName())
                    .role(MemberRole.USER)
                    .build();

            memberRepository.save(newMember);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity login(LoginDTO loginDTO) {

        Optional<Member> member = memberRepository.findById(loginDTO.getId());
        Member memberEntity = member.orElse(null);

        if (member == null) {
            return new ResponseEntity("사용 가능한 아이디 입니다.", HttpStatus.OK);
        }

        if (memberEntity.getPassword().equals(loginDTO.getPassword())) {
            return new ResponseEntity("success", HttpStatus.OK);
        }else {
            return new ResponseEntity(" 비밀번호가 일치하지 않습니다.", HttpStatus.OK);
        }
    }
}
