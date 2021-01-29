package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

// mainMethod-/eclipse /maven ����� ����Ǿ��ִ�.

// spring ȯ�濡�� junit �ڵ� ���� ==> junit �ڵ嵵 ������ ������ ���
@RunWith(SpringJUnit4ClassRunner.class)
//���� ������ �Ѱ��ֱ�
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {

	// test �� ������ ��ü
	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void getUserTest() {
		/*** Given ***/
		// ã�� ���̵� ��
		String userid = "brown";

		/*** When ***/
		// �׽�Ʈ �Ϸ��� �޼ҵ�
		UserVo userVo = userDao.getUser(userid);

		/*** Then ***/
		// �׽�Ʈ ��� ��
		assertEquals("����", userVo.getUsernm());
	}

}
