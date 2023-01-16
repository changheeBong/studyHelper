package study.helper.studyHelper.domain.user.service;

import org.springframework.lang.UsesSunHttpServer;
import study.helper.studyHelper.domain.user.constant.Role;
import study.helper.studyHelper.domain.user.dto.UserDTO;
import study.helper.studyHelper.domain.user.entity.User;

public interface UserService {

    void join(UserDTO userDTO);

    default User dtoToEntity(UserDTO userDTO){
        User user=User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .tel(userDTO.getTel())
                .birth(userDTO.getBirth())
                .createdDate(userDTO.getCreatedDate())
                .role(Role.ROLE_USER)
                .build();
    return user;
    }

}
