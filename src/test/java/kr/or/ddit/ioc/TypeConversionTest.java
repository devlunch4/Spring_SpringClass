package kr.or.ddit.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/typeConversion.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TypeConversionTest {

	private static final Logger logger = LoggerFactory.getLogger(TypeConversionTest.class);

	@Resource(name = "user")
	private UserVo user;

	@Test
	public void userTest() {
		// String age = 5;
		// int age = 5;
		// xml 에서는 값에 대한 파일/데이터값 정보가 없다.
		// 하지만 클래스 정보와 해당 필드의 정보를 알수있따.
		logger.debug("user.getReg_dt() : {}", user.getReg_dt());
		logger.debug("user.getReg_dt() : {}", user.getHire_dt());
		logger.debug("user.getPrice() : {}", user.getPrice());
		// assertEquals(5, user.getReg_dt());
	}

}
