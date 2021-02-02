package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

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

	@Test
	public void pageVoTest() throws Exception {
		PageVo pageVo = new PageVo();
		System.out.println("pageVo.getPage():" + pageVo.getPage());
		;
	}

	@Test
	public void pagingUser2Test() throws Exception {
		mockMvc.perform(get("/user/pagingUser").param("page", "2")).andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo")).andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}

	@Test // 상세보기 테스트
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
		mockMvc.perform(post("/user/userModify").param("user", "brown")).andExpect(view().name("user/userForm"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());
	}

}
