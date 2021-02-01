package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration(classes = {ComponentScanJavaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanTest {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "userService")
	private UserService userService;

	
	@Test
	public void userDaoImplSpringBeanTest() {
		// @Repository 어노페이션을 적용한 userDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록이 되었는지 확인
		assertNotNull(userDao);
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

	@Test
	public void userServiceImplSpringBeanTest() {
		// @Repository 어노페이션을 적용한 userDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록이 되었는지 확인
		assertNotNull(userService);
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

}
