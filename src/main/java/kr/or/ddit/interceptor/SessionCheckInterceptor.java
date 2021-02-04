package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 정장 접속인지 확인하는 로직 : session에 S_USER 속성이 있는지 확인/검사
		// session 값을 가져옴.
		if (request.getSession().getAttribute("S_USER") == null) {
			response.sendRedirect("/login/view");

			// 다음 controller에 보내지 않기떄문에 false로 설정
			return false;

		}
		return true;
	}
}
