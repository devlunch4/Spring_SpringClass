package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

// mainMethod-/eclipse /maven 모듈이 내장되어있다.

// spring 환경에서 junit 코드 실행 ==> junit 코드도 스프링 빈으로 등록
public class UserDaoTest extends ModelTestConfig {

	// test 시 가져올 객체
	@Resource(name = "userDao")
	private UserDao userDao;

	@Test // 사용자 아이디를 이용하여 특정 사용자 정보 조회
	public void getUserTest() {
		/*** Given ***/
		// 찾을 아이디 값
		String userid = "brown";

		/*** When ***/
		// 테스트 하려는 메소드
		UserVo userVo = userDao.selectUser(userid);

		/*** Then ***/
		// 테스트 결과 비교
		assertEquals("브라운", userVo.getUsernm());
	}

	// 사용자 아이디로 사용자 조회
	@Test
	public void selectUserTest() {
		/*** Given ***/
		String userid = "sally";

		/*** When ***/
		UserVo userVo = userDao.selectUser(userid);
		/*** Then ***/
		assertEquals("샐리", userVo.getUsernm());

	}

	// 전체 사용자 정보 조회
//	@Test
	public void selectAllUserTest() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.selectAllUser();

		/*** Then ***/
		assertEquals(16, userList.size());
	}

	// 페이지 처리
	@Test
	public void selectPagingUserTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(2, 5);

		/*** When ***/
		List<UserVo> userList = userDao.selectPagingUser(pageVo);

		/*** Then ***/
		assertEquals(5, userList.size());

	}

	// 사용자 전체수 조회
	@Test
	public void selectAllUserCntTest() {
		/*** Given ***/
		// UserDaoI userDao = new UserDao();

		/*** When ***/
		int userCnt = userDao.selectAllUserCnt();
		/*** Then ***/
		assertEquals(16, userCnt);

	}

	// 사용자 정보 수정
	@Test
	public void modifyUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("1234", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3",
				"testfilename", "testrealfilename");
		/*** When ***/
		int updateCunt = userDao.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCunt);
	}

	// 사용자 추가
	@Test
	public void insertUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("testUser", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3",
				"testfilename", "testrealfilename");
		/*** When ***/
		int insertUser = userDao.insertUser(userVo);
		/*** Then ***/
		assertEquals(1, insertUser);
	}

	// 사용자 삭제
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// 해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된상태
		String userid = "testUser";
		/*** When ***/
		int deleteCnt = userDao.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);
	}

}
