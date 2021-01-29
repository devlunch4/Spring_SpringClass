package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

// mainMethod-/eclipse /maven 모듈이 내장되어있다.

// spring 환경에서 junit 코드 실행 ==> junit 코드도 스프링 빈으로 등록
@RunWith(SpringJUnit4ClassRunner.class)
//설정 정보를 넘겨주기
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {

	// test 시 가져올 객체
	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void getUserTest() {
		/*** Given ***/
		// 찾을 아이디 값
		String userid = "brown";

		/*** When ***/
		// 테스트 하려는 메소드
		UserVo userVo = userDao.getUser(userid);

		/*** Then ***/
		// 테스트 결과 비교
		assertEquals("브라운", userVo.getUsernm());
	}

}
