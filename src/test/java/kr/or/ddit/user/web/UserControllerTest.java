package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.service.UserService;

public class UserControllerTest extends WebTestConfig {

	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser")).andExpect(view().name("user/allUser")).andExpect(status().isOk())
				.andExpect(model().attributeExists("userList")).andDo(print());
	}

}
