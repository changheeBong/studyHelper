package study.helper.studyHelper.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.helper.studyHelper.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    //회원가입시 중복된 회원이 있는지 검사
    Optional<User> findByEmail(String email);

}
