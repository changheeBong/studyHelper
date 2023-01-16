package study.helper.studyHelper.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.helper.studyHelper.domain.user.dto.UserDTO;
import study.helper.studyHelper.domain.user.entity.User;
import study.helper.studyHelper.domain.user.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional//로직을 처리하다가 에러발생하면,로직을 수행하기 이전상태로 콜백시킴
@RequiredArgsConstructor//final이나 @notnull붙은 필드에 생성자 생성&의존 주입
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(UserDTO userDTO) {
        //1.이메일 중복 검사
        Optional<User> findUserEmail=userRepository.findByEmail(userDTO.getEmail());
        log.info(findUserEmail);
        //2. 기존 회원일 경우 -에러처리
        if(findUserEmail.isPresent()){//이미 있는 이메일이면
            throw new IllegalStateException("이미 등록된 이메일입니다. 다른 이메일을 이용해주세요.");
        }
        //3.새로운 회원일 경우  -비밀번호 암호화후 DB에 저장
        else {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));//비밀번호 암호화
            User user = dtoToEntity(userDTO);//DB에 저장할때는 entity로 저장해야하기 때문에 DTO->entity로 바꾸는 작업 필요
            userRepository.save(user);//insert
        }
    }
}
