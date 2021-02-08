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

	}

}
