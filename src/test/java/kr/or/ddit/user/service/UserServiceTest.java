package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends ModelTestConfig {

	@Resource(name = "userService")
	private UserService userService;

	@Before
	public void setup() {
		// 테스트에서 사용할 신규 사용자 추가

		UserVo userVo = new UserVo("testUser", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3",
				"testfilename", "testrealfilename");

		userService.insertUser(userVo);

		// 신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userService.deleteUser("ddit_n");
	}

	@After
	public void tearDodwn() {
		userService.deleteUser("testUser");
	}

	
	@Test // 사용자 아이디를 이용하여 특정 사용자 정보 조회
	public void getUserTest() {
		/*** Given ***/
		// 찾을 아이디 값
		String userid = "brown";

		/*** When ***/
		// 테스트 하려는 메소드
		UserVo userVo = userService.selectUser(userid);

		/*** Then ***/
		// 테스트 결과 비교
		assertEquals("브라운", userVo.getUsernm());
	}

	// 전체 사용자 정보 조회
	@Test
	public void selectUserAllTest() {
		/*** Given ***/
		// UserServiceI userService = new UserService();

		/*** When ***/
		List<UserVo> userList = userService.selectAllUser();

		/*** Then ***/
		assertEquals(16, userList.size());
	}

	// 사용자 아이디가 존재하지 않을때 특정 사용자 조회 (확신이 없을때 확인을 위한 테스트 )
	@Test
	public void selectUserNotExsistTest() {
		/*** Given ***/
		// UserServiceI userService = new UserService();
		String userid = "brown";

		/*** When ***/
		UserVo user = userService.selectUser(userid);

		/*** Then ***/
		assertNotNull(user);
		// assertEquals("브라운", user.getUserid());
	}

	// 페이지 처리
	@Test
	public void selectPagingUserTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(2, 5);

		/*** When ***/
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		List<UserVo> userList = (List<UserVo>) map.get("userList");
		//int userCnt = (int) map.get("userCnt");
		/*** Then ***/
		assertEquals(5, userList.size());
		//assertEquals(16, userCnt);
	}

	// 사용자 정보 수정
	@Test
	public void modifyUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("testUser", "대덕인재", "pass", new Date(), "개발원ori", "대전 중앙로", "3층", "123", "testfilename",
				"testrealfilename");

		/*** When ***/
		int updateCunt = userService.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCunt);
	}

	// 사용자 추가
	@Test
	public void insertUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("ddit_n", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3",
				"testfileNM", "testrealfileNM");
		/*** When ***/
		int insertUser = userService.insertUser(userVo);
		/*** Then ***/
		assertEquals(1, insertUser);
	}

	// 삭제 테스트
	@Test
	public void deleteUserTest() {
		/*** Given ***/
		// 해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된상태
		String userid = "testUser";
		/*** When ***/
		int deleteCnt = userService.deleteUser(userid);
		/*** Then ***/
		assertEquals(1, deleteCnt);
	}

}
