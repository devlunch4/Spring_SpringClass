package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserServiceTest {

	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void getUserTest() {
		/*** Given ***/
		// 찾을 아이디 값
		String userid = "brown";

		/*** When ***/
		// 테스트 하려는 메소드
		UserVo userVo = userService.getUser(userid);

		/*** Then ***/
		// 테스트 결과 비교
		assertEquals("브라운", userVo.getUsernm());
	}

}
