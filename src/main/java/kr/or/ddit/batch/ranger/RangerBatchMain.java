package kr.or.ddit.batch.ranger;

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

public class RangerBatchMain {
	private static final Logger logger = LoggerFactory.getLogger(RangerBatchMain.class);

	// spring container == IOC container ==> container
	public static void main(String[] args) {
		logger.debug("INN RangerBatchMain.main()");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/kr/or/ddit/config/spring/batch-context.xml");

		// job 실행 ; jobLauncher, job

		// DI ,DL
		// 아래는 DL
		// JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher", );
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher", JobLauncher.class);
		Job job = context.getBean("rangersJob", Job.class);

		// 실행
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
