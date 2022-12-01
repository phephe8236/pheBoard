package hello.pheboard.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate //실제 값이 변경된 컬럼으로만 update쿼리를 만들어주는 어노테이션
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_Id", nullable = false)
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "board_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "board_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "board_admin_views", nullable = false)
    private int adminViews;

    @Column(name = "board_user_views", nullable = false)
    private int userViews;

    @Column(name = "board_likes", nullable = false)
    private int likes;

    @Enumerated(EnumType.STRING) //Enum 값을 String으로 저장하고 싶을 때 사용, 붙이지 않으면 정수가 저장됨(0,1,2....)
    @ManyToOne(fetch = FetchType.LAZY)
    /*
     다대일 관계에서 사용, 실무에서 모든 연관관계는 지연로딩(LAZY)로 설정
     즉시로딩(EAGER)은 예측이 어렵고, 어떤 SQL이 실행될 지 추적하기 어렵고 N+1문제가 자주 발생하기 때문
     */
    @JoinColumn(name = "member_id", nullable = false) // @joincolumn 외래키를 매핑할 때 사용
    private Member member;

    public void countAdmin() {
        this.adminViews++;
    }

    public void countUser() {
        this.userViews++;
    }
}
