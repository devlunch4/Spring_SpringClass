package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/component-scan.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanJavaTest {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	
	@Test
	public void userDaoImplSpringBeanTest() {
		// @Repository ������̼��� ������ userDaoImpl ������ ���� ���������� �����̳ʿ� ����� �Ǿ����� Ȯ��
		assertNotNull(userDao);
		UserVo userVo = userDao.getUser("brown");
		assertEquals("����", userVo.getUsernm());
	}

	@Test
	public void userServiceImplSpringBeanTest() {
		// @Repository ������̼��� ������ userDaoImpl ������ ���� ���������� �����̳ʿ� ����� �Ǿ����� Ȯ��
		assertNotNull(userService);
		UserVo userVo = userService.getUser("brown");
		assertEquals("����", userVo.getUsernm());
	}

}
