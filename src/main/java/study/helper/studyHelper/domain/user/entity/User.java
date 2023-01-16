package study.helper.studyHelper.domain.user.entity;

import lombok.*;
import study.helper.studyHelper.domain.user.constant.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)//자바enum타입을 엔티티의 속성으로 지정
    private Role role;

    private String tel;

    private String birth;

    private LocalDateTime createdDate;


    //회원정보 수정할때 사용할 함수
    public void change(String name,String tel,String birth){
        this.name=name;
        this.tel=tel;
        this.birth=birth;
    }

}
