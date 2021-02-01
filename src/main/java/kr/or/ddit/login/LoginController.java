package kr.or.ddit.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;

@RequestMapping("login")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("view")
	public String view() {
		logger.debug("iNN login controller >> view");
		return "login";
	}

	// String userid = req.getParameter("userid");
	// String pass = req.getParameter("pass");
	//@RequestMapping("process")
	public String process(String userid, String pass, int price) {
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("price : {}", price);
		return "";
	}
	
	//@RequestMapping("process")
	public String process(UserVo userVo) {
		logger.debug("userVo : {}", userVo);
		return "";
	}
}

