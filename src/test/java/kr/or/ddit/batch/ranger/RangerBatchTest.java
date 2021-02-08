package kr.or.ddit.batch.ranger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({ "classpath:/kr/or/ddit/config/spring/batch-context.xml",
		"classpath:/kr/or/ddit/config/spring/scheduler-context.xml",
		"classpath:/kr/or/ddit/config/spring/batch-test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RangerBatchTest {
	private static final Logger logger = LoggerFactory.getLogger(RangerBatchTest.class);

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void rangeTaskTest() throws Exception {
		logger.debug("INN rangeTaskTest()");

		// batch 테스트시 job 타입으로 등록된 스프링 빈은 하나여야 한다
		// job 이름을 명시하지 않아도 container에 하나의 배치 job이 등록 되어있다는
		// 가정이 있기 때문에 별도로 job 이름을 명시하지 않는다.
		JobExecution execution = jobLauncherTestUtils.launchJob();
// jobLauncherTestUtils 사용을 통해서 잡런처와 잡을 하나로 처리가 된다.

//
		//
		// 정상수행시 정상 문구 출력 확인
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
	}

}
