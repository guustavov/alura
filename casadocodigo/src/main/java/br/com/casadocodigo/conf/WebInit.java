package br.com.casadocodigo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class, JPAConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encondingFilter = new CharacterEncodingFilter();
        encondingFilter.setEncoding("UTF-8");

        return new Filter[] {encondingFilter};
    }
}
