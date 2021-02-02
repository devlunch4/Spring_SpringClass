package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// 필요한 스프링 빈 호출
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("allUser") // 모든 사용자 정보 조회
	public String allUser(Model model) {
		logger.debug("INN UserController allUser() :");
		// 객체 모델에 넣기
		model.addAttribute("userList", userService.selectAllUser());
		return "user/allUser";
	}

	// 1번 방법-guide
	@RequestMapping("pagingUser") // 페이징 처리
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
		logger.debug("INN UserController pagingUser()");
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		int userCnt = (int) map.get("userCnt");
		int pagination = (int) Math.ceil((double) userCnt / pageVo.getPageSize());
		model.addAttribute("userList", map.get("userList"));
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("pagination", pagination);
		return "user/pagingUser";
	}

	@RequestMapping("userForm") // 사용자 상세보기
	public String userForm(Model model, String userid) {
		logger.debug("INN UserController pagingUser() :");
		logger.debug("클릭된 userid값 : {}", userid);
		UserVo user = userService.selectUser(userid);
		model.addAttribute("user", user);
		return "user/userForm";
	}

	// 사용자 수정 // GET // 해당 수정 페이지로 이동
	@RequestMapping(path = "userModify", method = { RequestMethod.GET })
	public String userModifyGet(Model model, String userid) {
		UserVo user = userService.selectUser(userid);
		model.addAttribute("user", user);
		return "user/userModify";
	}

	// 사용자 수정 // POST // 해당 아이디에 대하여 값 업데이트
	@RequestMapping(path = "userModify", method = { RequestMethod.POST })
	public String userModifyPost(UserVo userVo, MultipartFile profile, Model model,
			@RequestParam("reg_dt2") String reg_dt2) {
		logger.debug("INN UserController pagingUser() :");
		logger.debug("수정할 userid: {}", userVo.getUserid());
		// 파일 세팅 설정
		String originalFileName = "";
		String fileExtension = "";
		String realFileName = "";
		// profile.isEmpty()
		if (profile.getSize() > 0) {
			originalFileName = profile.getOriginalFilename();
			int pos = originalFileName.lastIndexOf(".");
			fileExtension = originalFileName.substring(pos + 1);
			realFileName = UUID.randomUUID().toString() + fileExtension;
			try {
				// 저장위치 지정 및 저장
				profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// file 컬럼 2부분 설정 / 날짜 부분 재설정
		userVo.setFilename(originalFileName);
		userVo.setRealfilename(realFileName);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date parseDate = null;
		try {
			parseDate = dateFormat.parse(reg_dt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userVo.setReg_dt(parseDate);
		// 사용자 수정 sql 시행
		model.addAttribute("user", userVo);
		int updateCnt = userService.modifyUser(userVo);
		if (updateCnt == 1) {
			// 성공시 파일 저장 및 상세정보로 이동
			logger.debug("사용자 정보 수정 성공");
			return "user/userForm";
		} else {
			// 실패시 수정 페이지로 이동
			logger.debug("사용자 정보 수정 실패");
			return "user/userModify";
		}
	}

	// 사용자 삭제 POST
	@RequestMapping(path = "userDelete", method = { RequestMethod.POST })
	public String userDeletePost(Model model, String userid) {
		logger.debug("INN UserController.userDeletePost()");
		int deleteCnt = 0;
		try {
			deleteCnt = userService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = -1;
		}
		if (deleteCnt == 1) {
			logger.debug("사용자 {} 삭제 완료", userid);
			model.addAttribute("userList", userService.selectAllUser());
			return "user/allUser";
		} else {
			return "user/userModify";
		}
	}

	// 사용자 신규 등록 POST
	@RequestMapping(path = "userRegist", method = { RequestMethod.POST })
	public String userRegistPost(UserVo userVo, MultipartFile profile, Model model) {
		logger.debug("INN UserController.userRegistPost()");
		// 파일 세팅 설정
		String originalFileName = "";
		String fileExtension = "";
		String realFileName = "";
		// profile.isEmpty()
		if (profile.getSize() > 0) {
			originalFileName = profile.getOriginalFilename();
			int pos = originalFileName.lastIndexOf(".");
			fileExtension = originalFileName.substring(pos + 1);
			realFileName = UUID.randomUUID().toString() + fileExtension;
			try {
				// 저장위치 지정 및 저장
				profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 신규 등록에 따른 파일/날짜 컬럼 값 설정
		userVo.setFilename(originalFileName);
		userVo.setRealfilename(realFileName);
		userVo.setReg_dt(new Date());
		// 등록 sql 시행
		int insertUser = userService.insertUser(userVo);
		if (insertUser == 1) {
			// 성공시 파일 저장 및 상세정보로 이동
			logger.debug("사용자 정보 등록 성공");
			model.addAttribute("user", userVo);
			return "user/userForm";
		} else {
			// 실패시 수정 페이지로 이동
			logger.debug("사용자 정보 등록 실패");
			model.addAttribute("user", userVo);
			return "user/userRegist";
		}
	}

	// 사용자 등록 GET
	@RequestMapping(path = "userRegist", method = { RequestMethod.GET })
	public String userRegistGet() {
		logger.debug("INN UserController.userRegistGet()");
		return "user/userRegist";
	}

}
