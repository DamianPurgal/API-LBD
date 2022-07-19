package lbd.fissst.api_lbd.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lbd.fissst.api_lbd.security.response.AuthorizationResponse;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthorizationStudentFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String headerValue = request.getHeader("role");

        if(headerValue != null && (headerValue.equals("STUDENT_ROLE") || headerValue.equals("TEACHER_ROLE"))){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    new AuthorizationResponse(
                            "User unauthorized!"
                    )
            );
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
