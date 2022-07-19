package lbd.fissst.api_lbd.security.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lbd.fissst.api_lbd.security.response.AuthorizationResponse;
import lbd.fissst.api_lbd.service.definition.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@AllArgsConstructor
public class AuthorizeInterceptor implements HandlerInterceptor {

    private final AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();
        String roleHeaderValue = request.getHeader("role");

        if(authorizationService.isAuthorized(URI, roleHeaderValue)){
            return true;
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    new AuthorizationResponse(
                            "User unauthorized!"
                    )
            );

            return false;
        }
    }

}
