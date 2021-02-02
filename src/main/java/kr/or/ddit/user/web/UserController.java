package kr.or.ddit.user.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVo;
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

	// 1번 방법-guide
	@RequestMapping("pagingUser")
	public String paginUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		logger.debug("(page:{}, pageSize:{}", page, pageSize);
		// logger.debug("(price:{}", price); // price 삭제

		PageVo pageVo = new PageVo(page, pageSize);
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		model.addAllAttributes(map);

		return "user/pagingUser";
	}

	// 2번 방법
	// @RequestMapping("pagingUser")
	public String paginUser(Model model, PageVo pageVo) {
		logger.debug("진입 UserController pagingUser() :");

		Map<String, Object> map = userService.selectPagingUser(pageVo);
		int userCnt = (int) map.get("userCnt");
		int pagination = (int) Math.ceil((double) userCnt / pageVo.getPageSize());

		model.addAttribute("userList", map.get("userList"));
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("pagination", pagination);

		return "user/pagingUser";
	}

}
