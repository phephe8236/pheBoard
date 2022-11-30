package hello.pheboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpFormDto {   //요청시 데이터를 받아올 DTO

    private String id;
    private String password;
    private String name;

    /*
        Entity는 데이터베이스와 맞닿아 있는 핵심 클래스
        DTO를 사용하여 View단과 DB단을 철저히 분리시킨다.
        화면마다 null 체크 등 요구사항이 다를텐데, 이런 요구사항들을 Entity에 명시하면 알아보기도 힘들고 유지보수도 힘들다.
     */
}
