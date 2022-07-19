package lbd.fissst.api_lbd.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

public class ElapsedTimeFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ElapsedTimeFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LocalTime initialTime = LocalTime.now();
        try{
            filterChain.doFilter(request, response);
        }finally{
            String elapsedTime =  Duration.between(initialTime, LocalTime.now()).toString();
            LOG.info("Request time: " + elapsedTime);
        }
    }

}
