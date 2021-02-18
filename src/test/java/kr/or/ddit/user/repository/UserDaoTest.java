package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

// mainMethod-/eclipse /maven 모듈이 내장되어있다.

// spring 환경에서 junit 코드 실행 ==> junit 코드도 스프링 빈으로 등록
public class UserDaoTest extends ModelTestConfig {

	// test 시 가져올 객체
	@Resource(name = "userDao")
	private UserDao userDao;

	
	
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	@Before
	public void setup() {
		
		//initData.sql을 실행 : 스프링에서 제공하는 ResourceDatabasePopulator
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
		//populator 를 통해 실행시킬 sql 파일 지정
		populator.addScript(new ClassPathResource("kr/or/ddit/config/db/initData.sql"));
		//populator 를 실행
		DatabasePopulatorUtils.execute(populator, dataSource);

		
		//script 가 실행하다가 에러가 발생하면 더이상 진행하지 않고 멈춤
		populator.setContinueOnError(false);
		
		// userDao = new UserDao();

		// 테스트에서 사용할 신규 사용자 추가
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "대덕", "대전 중구 중앙로 76", "4층",
				"34940", "brown.png", "uuid-generated-filename.png");

		//userDao.insertUser(userVo);

		// 신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userDao.deleteUser("ddit_n");

	}

	@After
	public void tearDodwn() {
		userDao.deleteUser("testUser");
	}
	
	
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
		assertEquals(17, userList.size());
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
		assertEquals(17, userCnt);

	}

	// 사용자 정보 수정
	@Test
	public void modifyUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("testUser", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3", "testfilename",
				"testrealfilename");
		/*** When ***/
		int updateCunt = userDao.modifyUser(userVo);

		/*** Then ***/
		assertEquals(1, updateCunt);
	}

	// 사용자 추가
	@Test
	public void insertUserTest() {
		/*** Given ***/
		UserVo userVo = new UserVo("testUser2", "테스터", "testpass", new Date(), "테스터별명", "주소11", "주소22", "3",
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
