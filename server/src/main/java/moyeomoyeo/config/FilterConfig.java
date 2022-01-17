/*
package moyeomoyeo.config;

import moyeomoyeo.filter.Filter_first;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter_first> filter1(){
        FilterRegistrationBean<Filter_first> bean = new FilterRegistrationBean<>(new Filter_first());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); //숫자가 낮은거 부터
        return bean;
    }
}
*/
