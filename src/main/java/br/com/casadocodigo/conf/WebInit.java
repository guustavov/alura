package br.com.casadocodigo.conf;

import br.com.casadocodigo.infra.FileSaver;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class,
                WebConfig.class,
                JPAConfig.class,
                FileSaver.class,
                SecurityConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter()};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter("spring.profiles.active", "dev");
    }
}
