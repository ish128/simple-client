package client;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static int MAX_FILE_ZIZE = 10 * 1024 * 1024;
	
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
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) { 
        // Optionally also set maxFileSize, maxRequestSize, filezSizeThreshold 
    	//fileSizeThreshold - 업로드하는 파일이 임시로 파일로 저장되지 않고 메모리에서 바로 스트림으로 전달되는 크기의 한계를 나타낸다
        registration.setMultipartConfig(new MultipartConfigElement("/tmp", MAX_FILE_ZIZE, MAX_FILE_ZIZE, 0));
        registration.setInitParameter("enableLoggingRequestDetails", "true");
    }
}