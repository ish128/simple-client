package client;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { AppContextConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() { 
        return new Filter[] {new CharacterEncodingFilter("UTF-8", true)}; // dispatcher servlet 이 필터 등록 
    }
    
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) { 
//        // Optionally also set maxFileSize, maxRequestSize, filezSizeThreshold
//        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
//        registration.setInitParameter("enableLoggingRequestDetails", "true"); 
//    }
}