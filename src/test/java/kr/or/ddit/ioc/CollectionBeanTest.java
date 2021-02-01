package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.config.ModelTestConfig;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectionBeanTest extends ModelTestConfig{

	@Resource(name = "collectionBean")
	private CollectionBean collectionBean;

	@Test
	public void collectionBeanTest() {
		// collectionBean 스프링빈이 정상적으로 생성 되었는지확인
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getList());
		assertEquals(3, collectionBean.getList().size());
		assertEquals("sally", collectionBean.getList().get(1));
		assertEquals("브라운", collectionBean.getMap().get("usernm"));
	}

}
