package kr.or.ddit.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.config.WebTestConfig;

public class MvcControllerTest extends WebTestConfig {

	@Test
	public void fileuploadViewTest() throws Exception {
		mockMvc.perform(get("/mvc/fileupload/view")).andExpect(view().name("file/view")).andDo(print());
	}

	@Test
	public void fileuploadTest() throws Exception {
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/test.jpg");
		// 파일 준비
		MockMultipartFile file = new MockMultipartFile("picture", "test.jpg", "image/jpg", resource.getInputStream());
		mockMvc.perform(fileUpload("/mvc/fileupload/upload/").file(file).param("userid", "uploadtestid"))
				.andExpect(view().name("file/view")).andDo(print());

	}
}
