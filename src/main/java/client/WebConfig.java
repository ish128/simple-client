package client;

 

 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 

 

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig implements WebMvcConfigurer {
	
	private static int MAX_FILE_ZIZE = 10 * 1024 * 1024;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/qna/**");
    }
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)  { 
		registry.jsp("/WEB-INF/views/",".jsp");
	} 
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/"); 
	}

//	@Bean
//    public MultipartResolver multipartResolver() { 
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(); 
//		multipartResolver.setMaxUploadSize(MAX_FILE_ZIZE);
//		multipartResolver.setMaxUploadSizePerFile(MAX_FILE_ZIZE);
//		multipartResolver.setMaxInMemorySize(0);
//		return multipartResolver;
//   }
	
	@Bean
	public MultipartResolver multipartResolver() { 
		return new StandardServletMultipartResolver();
	}
		
	
}
