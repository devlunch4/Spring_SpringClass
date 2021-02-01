package kr.or.ddit.test.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//설정 정보를 넘겨주기
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class ModelTestConfig {

}
