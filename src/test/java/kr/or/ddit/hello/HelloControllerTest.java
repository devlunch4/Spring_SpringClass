package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//java -gui swing, awt, java fx, java swt

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

//@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
//		"classpath:/kr/or/ddit/config/spring/root-context.xml" })
//@WebAppConfiguration // 스프링 환경을 web기반의 application context로 생성, 컨트롤러 환경 테스트시 필요
//@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig {
	// 주입받기
	// @Resource(name = "helloController")

	// 스프링빈중 대입 가능한 타입의 스프링 빈을 주입한다.
	// 만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지칭할수 있다.
	// ==> @Resource 어노테이션 하나를 사용했을 때와 동일하다

	/*
	 * webtestconfig 에 붙여넣음
	 * 
	 * @Autowired private WebApplicationContext context;
	 * 
	 * private MockMvc mockMvc;
	 * 
	 * // 테스트 전 설정
	 * 
	 * @Before public void setup() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(context).build(); }
	 */

	// localhost/hello/view
	@Test
	public void viewTest() throws Exception {
		/*** Given ***/
		/*** When ***/
		/*** Then ***/
		// GWT 생략 MockMvc 로 는 한줄로 처리가능.

		// mockMvc를 사용하여 한번에 표현 가능- andExpect>> 요청을 보냈을때 예상이된다.
		// isOk() >> code is HttpStatus.OK (200). 응답 200 으로 정상 처리
		// view() >> the selected view name
		// model().attributeExists 속성
		// buildup pattern 으로 한줄로 표현 가능.
		MvcResult mvcResult = mockMvc.perform(get("/hello/view")).andExpect(status().isOk()).andExpect(view().name("hello"))
				.andExpect(model().attributeExists("userVo")).andDo(print()).andReturn();
		// 기존 테스트와 다른 것은 준비과정과 한줄로 표현, 기대값의 예상 표현이 다르다.
		
		ModelAndView mav = mvcResult.getModelAndView();
		//assertEquals("기대값", "실제값");
		assertEquals("hello", mav.getViewName());
		UserVo userVo =  (UserVo) mav.getModel().get("userVo");
		assertEquals("brown", userVo.getUserid());
	}

}
