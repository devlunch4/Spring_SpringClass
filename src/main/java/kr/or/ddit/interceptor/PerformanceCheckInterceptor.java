package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckInterceptor.class);

	long startTime = 0;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 시작 시간을 기록
		long startTime = System.nanoTime();
		request.setAttribute("startTime", startTime);

		// 요청을 다음 interceptor or controller에게 위임할지 여부 반환
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// controller 메소드가 실행된 이후 실행되는 영역
		long endTime = System.nanoTime();
		long startTime = (long) request.getAttribute("startTime");
		logger.debug("uri: {}, duration : {}", request.getRequestURI(), endTime - startTime);
	}

}
