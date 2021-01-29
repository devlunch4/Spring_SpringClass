package kr.or.ddit.hello;

import static org.junit.Assert.*;

import javax.annotation.Resource;

//java -gui swing, awt, java fx, java swt

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
		"classpath:/kr/or/ddit/config/spring/root-context.xml" })
@WebAppConfiguration // 스프링 환경을 web기반의 application context로 생성, 컨트롤러 환경 테스트시 필요
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {

	// 주입받기
	//@Resource(name = "helloController")
	
	//스프링빈중 대입 가능한 타입의 스프링 빈을 주입한다. 
	//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해 
	//특정 스프링 빈의 이름을 지칭할수 있다.
	// ==> 
	@Autowired 
	private HelloController helloController;

	@Test
	public void helloControllertest() {
		assertNotNull(helloController);
	}

}
