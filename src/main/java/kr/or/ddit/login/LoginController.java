package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("login")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(path = "view", method = {RequestMethod.GET})
	public String view() {
		logger.debug("iNN login controller >> view");
		return "login";
	}

	// String userid = req.getParameter("userid");
	// String pass = req.getParameter("pass");
	// @RequestMapping("process")
	public String process(String userid, String pass, int price) {
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("price : {}", price);
		return "";
	}

	// post만 처리하도 설정
	@RequestMapping(path = "process", method = {RequestMethod.POST})
	public String process(UserVo userVo, HttpSession session) {
		logger.debug("userVo : {}", userVo);

		// 아이디 가져오기
		UserVo dbUser = userService.selectUser(userVo.getUserid());

		logger.debug(dbUser.getPass() + "/" + userVo.getPass());
		if (dbUser != null && userVo.getPass().equals(dbUser.getPass())) {
			// 로그인 성공시
			// 세션 내장객체 호출
			session.setAttribute("S_USER", dbUser);
			return "main";
		} else {
			// 로그인 실패시
			return "redirect:/login/view";
		}
	}
}
