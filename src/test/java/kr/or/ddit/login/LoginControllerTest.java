package kr.or.ddit.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.test.config.WebTestConfig;

public class LoginControllerTest extends WebTestConfig {

	@Test
	public void viewTest() throws Exception {

		// localhost/login/view + enter ==> get
		mockMvc.perform(get("/login/view")).andExpect(status().isOk()).andExpect(view().name("login"));

		// perform(get("/hello/view")).andExpect(status().isOk()).andExpect(view().name("login"));

	}

	@Test
	public void processTest() throws Exception {
		mockMvc.perform(get("/login/process").param("userid", "brown").param("pass", "brownPass").param("price", "1000"))
				.andExpect(view().name(""));
	}

}
