package lbd.fissst.api_lbd.security.configuration;

import lbd.fissst.api_lbd.security.filter.AuthorizationStudentFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<AuthorizationStudentFilter> authorizationStudentFilter(){
        FilterRegistrationBean<AuthorizationStudentFilter> filterBean
                = new FilterRegistrationBean<>();

        filterBean.setName("AuthorizationStudentFilter");
        filterBean.addUrlPatterns("/api/student/*");
        filterBean.setFilter(new AuthorizationStudentFilter());
        filterBean.setOrder(1);

        return filterBean;
    }
}
