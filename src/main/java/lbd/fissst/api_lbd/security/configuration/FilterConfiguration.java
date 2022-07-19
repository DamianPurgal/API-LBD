package lbd.fissst.api_lbd.security.configuration;

import lbd.fissst.api_lbd.security.filter.AuthorizationStudentFilter;
import lbd.fissst.api_lbd.security.filter.AuthorizationTeacherFilter;
import lbd.fissst.api_lbd.security.filter.ElapsedTimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    /*
    @Bean
    public FilterRegistrationBean<AuthorizationStudentFilter> authorizationStudentFilter(){
        FilterRegistrationBean<AuthorizationStudentFilter> filterBean
                = new FilterRegistrationBean<>();

        filterBean.setName("AuthorizationStudentFilter");
        filterBean.addUrlPatterns("/api/student/*");
        filterBean.setFilter(new AuthorizationStudentFilter());
        filterBean.setOrder(2);

        return filterBean;
    }
     */

    @Bean
    public FilterRegistrationBean<ElapsedTimeFilter> elapsedTimeFilter(){
        FilterRegistrationBean<ElapsedTimeFilter> filterBean
                = new FilterRegistrationBean<>();

        filterBean.setName("ElapsedTimeFilter");
        filterBean.setFilter(new ElapsedTimeFilter());
        filterBean.setOrder(1);

        return filterBean;
    }

    /*
    @Bean
    public FilterRegistrationBean<AuthorizationTeacherFilter> authorizationTeacherFilter(){
        FilterRegistrationBean<AuthorizationTeacherFilter> filterBean
                = new FilterRegistrationBean<>();

        filterBean.setName("AuthorizationTeacherFilter");
        filterBean.addUrlPatterns("/api/teacher/*");
        filterBean.setFilter(new AuthorizationTeacherFilter());
        filterBean.setOrder(3);

        return filterBean;
    }
    */
}
