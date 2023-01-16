package study.helper.studyHelper.domain.user.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import study.helper.studyHelper.domain.user.constant.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {


    private Long id;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message= "이메일형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message="이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=6,max=16,message = "비밀번호는 6자 이상, 16자 이하로 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 6자~16자의 비밀번호여야 합니다.")
    private String password;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",
            message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String tel;

    private Role role;

    private String birth;

    private LocalDateTime createdDate;


}
