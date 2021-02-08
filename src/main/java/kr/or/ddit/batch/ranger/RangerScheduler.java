package kr.or.ddit.batch.ranger;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerScheduler {
	private static final Logger logger = LoggerFactory.getLogger(RangerScheduler.class);

	// DI
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	@Resource(name = "rangersJob")
	private Job job;

	public void logging() {
		// 반복적으로 호출할 로거 내용
		logger.debug("INN RangerScheduler.logging()");
		logger.debug("nowTime : {}", new Date());

	}

	public void rangerTask() {
		// 실행
		logger.debug("=====================================================================================");
		logger.debug("INN RangerScheduler.rangerTask()");
		logger.debug("nowTime : {}", new Date());
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		logger.debug("=====================================================================================");
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/kr/or/ddit/config/spring/batch-context.xml",
						"classpath:/kr/or/ddit/config/spring/scheduler-context.xml" });
		// DL
		// jobLauncher = (JobLauncher) context.getBean("jobLauncher",
		// JobLauncher.class);
		// job = context.getBean("rangersJob", Job.class);

		// 위 resource 어노테이션을 지우고 아래 코드 작성후 실행은 되지만 이전에 만든 xml 빈과는 다른 컨터이너이다.
		// 어노테이션은 안된다면 따로 스캔을 꼭 넣어야 한다. >> 아래 부분
		// xml 내
		// <!-- @Resource @Autowired @ Inject 어노페이션을 통해 주입되는 빈을 처리해준다. -->
		// <!-- <context:annotation-config/> 엘레멘트를 사용할 경우 별도 선언은 필요 없다. -->
		// <context:annotation-config />
		// RangerScheduler rangerScheduler = new RangerScheduler();
		// rangerScheduler.jobLauncher = (JobLauncher)
		// context.getBean("jobLauncher",JobLauncher.class);
		// rangerScheduler.job = context.getBean("rangersJob", Job.class);
	}

}
