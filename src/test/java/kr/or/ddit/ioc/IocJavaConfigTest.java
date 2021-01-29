package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.IocJavaConfig;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration(classes= {IocJavaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class IocJavaConfigTest {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "userServiceCons")
	private UserService userServiceCons;

	@Resource(name = "userService")
	private UserService userService2;

	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype;

	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype2;

	@Resource(name = "dbConfig")
	private DbConfig dbConfig;

	@Test
	public void userServiceConsTest() {
		// userServiceCons 스프링 빈이 생성되었는지 확인하는 테스트
		assertNotNull(userServiceCons);
	}

	@Test
	public void beanScopeTest() {
		// 디자인 패턴 개념으로 보면 두개의 객체는 한 클래스로 부터 나왔으므로
		//하지만 스프링의 singleton개념은 bean 엘레멘트를 기준을 ㅗ하나의 객체가 생성된다.
		assertNotEquals(userService, userServiceCons);

	}

	@Test
	public void beanScopeTest2() {
		// 동일한 스프링 빈을 주입받았으므로 userService, userService2 은 같은 객체이다.
		assertEquals(userService, userService2);
	}

	@Test
	public void beanScopePrototypeTest() {
		// 동일한 userServicePrototype 빈을 주입 받았는데 (scope : Prototype) 요청할때마다 새로운 것을 생성
		assertNotEquals(userServicePrototype, userServicePrototype2);
	}

	@Test
	public void propertyPlaceholderTest() {
		// dbinfo.properties 의 정보의 주입이 잘 되었는지
		assertNotNull(dbConfig);
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("PC20", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());

	}

}
