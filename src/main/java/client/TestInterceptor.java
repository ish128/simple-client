package client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import client.controller.QnaForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub  
		
		if(request.getAttribute("qna")!=null) {
			log.info("inteceptor qna exist : {}", request.getAttribute("qna"));
		} 
		
		request.setAttribute("startTime", System.currentTimeMillis());
		System.out.println("startTime::"+ request.getAttribute("startTime"));
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception { 
        request.setAttribute("endTime", System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = (Long) request.getAttribute("endTime");
        System.out.println("Time Spent in Handler in ms : " + (endTime - startTime));
    }
	

}
