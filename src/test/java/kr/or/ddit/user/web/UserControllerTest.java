package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;

public class UserControllerTest extends WebTestConfig {

	@Test // 모든 사용자 정보 조회
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser")).andExpect(view().name("user/allUser")).andExpect(status().isOk())
				.andExpect(model().attributeExists("userList")).andDo(print());
	}

	@Test // 페이징 처리
	public void pagingUserTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser")).andExpect(view().name("user/pagingUser")).andExpect(status().isOk())
				.andExpect(model().attributeExists("userList")).andExpect(model().attributeExists("pageVo"))
				.andExpect(model().attributeExists("pagination")).andDo(print());
	}

	@Test // 페이징 처리
	public void pageVoTest() throws Exception {
		PageVo pageVo = new PageVo();
		System.out.println("pageVo.getPage():" + pageVo.getPage());
		;
	}

	@Test // 페이징 처리
	public void pagingUser2Test() throws Exception {
		mockMvc.perform(get("/user/pagingUser").param("page", "2")).andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo")).andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}

	@Test // 사용자 상세보기 테스트
	public void userFormTest() throws Exception {
		mockMvc.perform(get("/user/userForm").param("userid", "sally")).andExpect(view().name("user/userForm"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());
	}

	@Test // 사용자 수정 GET 테스트
	public void userModifyGetTest() throws Exception {
		mockMvc.perform(get("/user/userModify").param("userid", "sally")).andExpect(view().name("user/userModify"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());
	}

	@Test // 사용자 수정 POST 테스트
	public void userModifyPostTest() throws Exception {
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/test.jpg");
		MockMultipartFile profile = new MockMultipartFile("profile", "test.jpg", "image/jpg",
				resource.getInputStream());

		// mockMvc.perform(post("/user/userModify").param("userid",
		// "brown").param("pass", "brownPass")).andExpect(view().name("user/userForm"))
		// .andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());

		mockMvc.perform(fileUpload("/user/userModify").file(profile).param("userid", "brown").param("pass", "brownPass")
				.param("userid", "brown").param("usernm", "브라운").param("reg_dt2", "2020.01.01").param("alias", "곰")
				.param("addr1", "addr1").param("addr2", "addr2").param("zipcode", "zipcode"))
				.andExpect(view().name("user/userModify")).andExpect(status().isOk())
				.andExpect(model().attributeExists("user")).andDo(print());
	}

	@Test // 사용자 삭제 POST 테스트
	public void userDeletePostTest() throws Exception {
		mockMvc.perform(post("/user/userDelete").param("userid", "123123")).andExpect(view().name("user/allUser"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("userList")).andDo(print());
	}

	//@Test // 사용자 신규 등록 POST 테스트
	public void userRegistPostTest() throws Exception {
		 ClassPathResource resource = new
		 ClassPathResource("kr/or/ddit/upload/test.jpg");
		 MockMultipartFile profile = new MockMultipartFile("profile", "test.jpg",
		 "image/jpg", resource.getInputStream());

		mockMvc.perform(fileUpload("/user/userRegist").file(profile).param("userid", "1231231").param("usernm", "123123").param("pass", "123123")
				.param("reg_dt2", "2020.01.01").param("alias", "tester").param("addr1", "addr1").param("addr2", "addr2")
				.param("zipcode", "zipcode")).andExpect(view().name("redirect:/user/pagingUser"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());
	}

	// 사용자 등록 GET 테스트

}
