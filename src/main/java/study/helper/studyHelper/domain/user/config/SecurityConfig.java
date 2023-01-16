package study.helper.studyHelper.domain.user.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.Access;
import javax.sql.DataSource;
import java.nio.file.AccessDeniedException;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)// @PreAuthorize 와 @PostAuthorize 메소드 호출 이전 이후에 권한을 확인할 수 있다.
public class SecurityConfig {

    private final DataSource dataSource;
    //private final CustomUserDetailService userDetailService;


    @Bean//비밀번호 암호화
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        //static 디렉토리의 하위 파일 목록은 인증 무시
//        web.ignoring().antMatchers("/css/**","js/**","/img/**","/lib/**");
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        log.info("--------------security config--------------");

        httpSecurity
                //로그인 설정
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("id")//로그인시 username으로 이용할 값 중복검사
                .defaultSuccessUrl("/main")//로그인 성공시 이동할 url
                .failureUrl("/user/login/error")//로그인 실패시 이동할 url

                .and()//로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/main")

                .and()//페이지 접근 권한 설정
                .authorizeRequests()
                .mvcMatchers("/main","/user/login","/user/logout").permitAll()
                .mvcMatchers().hasRole("ROLE_USER")
                .mvcMatchers("/admin").hasRole("ROLE_ADMIN")
                .anyRequest().authenticated()//이외의 다른건 인증 필요

                .and()//403예외처리
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                ;
        return httpSecurity.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }


}
