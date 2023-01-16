package study.helper.studyHelper.domain.user.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j2 // AccessDeniedHandler는 서버에 요청을 할때 접근가능한지 권한 체크 후 접근 할수 없는 요청이 왔을때 동작
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("------ACCESS DENIED-------");

        response.setStatus(HttpStatus.FORBIDDEN.value());

        //json request이었는지 확인
        String contentType=request.getHeader("Content-Type");
        boolean jsonRequest=contentType.startsWith("application/json");
        log.info("isJson:"+jsonRequest);

        //일반 request
        if(!jsonRequest){
            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }
    }




}
