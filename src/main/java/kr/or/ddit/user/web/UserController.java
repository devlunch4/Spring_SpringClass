package kr.or.ddit.user.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// 필요한 스프링 빈 호출
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("allUser")
	public String allUser(Model model) {
		logger.debug("진입 UserController allUser() :");
		// 객체 모델에 넣기
		model.addAttribute("userList", userService.selectAllUser());
		return "user/allUser";
	}

}
