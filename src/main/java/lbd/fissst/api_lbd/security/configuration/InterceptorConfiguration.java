package lbd.fissst.api_lbd.security.configuration;

import lbd.fissst.api_lbd.security.interceptor.AuthorizeInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@AllArgsConstructor
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    private AuthorizeInterceptor authorizeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor);
    }
}
