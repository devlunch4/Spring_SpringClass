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
		// userServiceCons ������ ���� �����Ǿ����� Ȯ���ϴ� �׽�Ʈ
		assertNotNull(userServiceCons);
	}

	@Test
	public void beanScopeTest() {
		// ������ ���� �������� ���� �ΰ��� ��ü�� �� Ŭ������ ���� �������Ƿ�
		//������ �������� singleton������ bean ������Ʈ�� ������ ���ϳ��� ��ü�� �����ȴ�.
		assertNotEquals(userService, userServiceCons);

	}

	@Test
	public void beanScopeTest2() {
		// ������ ������ ���� ���Թ޾����Ƿ� userService, userService2 �� ���� ��ü�̴�.
		assertEquals(userService, userService2);
	}

	@Test
	public void beanScopePrototypeTest() {
		// ������ userServicePrototype ���� ���� �޾Ҵµ� (scope : Prototype) ��û�Ҷ����� ���ο� ���� ����
		assertNotEquals(userServicePrototype, userServicePrototype2);
	}

	@Test
	public void propertyPlaceholderTest() {
		// dbinfo.properties �� ������ ������ �� �Ǿ�����
		assertNotNull(dbConfig);
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("PC20", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());

	}

}
