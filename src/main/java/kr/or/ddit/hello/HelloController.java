package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@RequestMapping("hello")
@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Resource(name = "userService")
	private UserService userService;

	@ModelAttribute(name = "rangers")
	public List<String> rangers() {
		List<String> list = new ArrayList<>();
		list.add("brown");
		list.add("sally");
		list.add("james");
		list.add("cony");
		list.add("moon");

		return list;
	}

	// localhost/hello/view ==> localhost/view
	// localhost/hello/view.do
	@RequestMapping("view")
	public String view(Model model) {
		logger.debug("INN HelloController view()");
		// logger.debug("진입 HelloController view() : {}", userService.getUser("brown"));

		// request.setAttribute("userVo", userService.getUser("brown"));
		model.addAttribute("userVo", userService.selectUser("brown"));
		return "hello";
	}

	// 기존 서블릿 적용---이제는 미사용
//	@RequestMapping("view")
//	public String view(HttpServletRequest request) {
//		logger.debug("진입 HelloController view() : {}", userService.getUser("brown"));
//
//		request.setAttribute("userVo", userService.getUser("brown"));
//		//model.addAttribute("userVo", userService.getUser("brown"));
//		return "hello";
//	}

}
