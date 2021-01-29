package kr.or.ddit.ioc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserServiceImpl;

//스프링 프레임워크에 해당 자바파일이
//스프링 설정 파일임을 알려준다

@PropertySource(value = { "classpath:/kr/or/ddit/config/db/dbinfo.properties" })
@Configuration
public class IocJavaConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	// 메소드 : 스프링 빈으로 만들 객체를 반환하는 메소드를 생성
	// ---- 메소드에 @Bean 어노테이션을 적용
	// ---- @Bean 어노테이션에 별다른 옵션을 적용하지 않으면 생성된 스프링 빈의 이름은
	// ---- 메소드 이름으로 적용된다. (@Bean 어노테이션의 name 속성을 통해 스프링 빈 이름 설정 가능)

	// <bean id="userDao" class="kr.or.ddit.user.repository.UserDaoImpl">
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}

////////	

	// <bean
	// id="userService"class="kr.or.ddit.user.service.UserServiceImpl"><property
	// name = "userDao"ref="userDao"></property></bean>
	// UserService userService = new userServiceImpl();
	// userService.getUser("brown");
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		// userService.setUserDao(new UserDaoImpl()); 는 아래 코드의 빈 사용과 같다
		// 즉 위에 @bean 선언한걸 가져온다.
		userService.setUserDao(userDao());
		return userService;
	}

	//////

	// ioc.xml part 생성자 주입
	// <bean id="userServiceCons" class="kr.or.ddit.user.service.UserServiceImpl">
	// <constructor-arg ref="userDao"></constructor-arg></bean>
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
	}

	////////

//	ioc.xml part prototype : 해당 빈을 dl,di 할때 마다 매번 새롭게 만든 객체를 반환
//	<bean id="userServicePrototype"  class="kr.or.ddit.user.service.UserServiceImpl" 
	// scope="prototype"> <property name="userDao" ref="userDao"></property>
//	</bean>

	// @Scope("prototype")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean
	public UserServiceImpl userServicePrototype() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		return userService;
	}

//	<bean id="collectionBean" class="kr.or.ddit.ioc.CollectionBean">
//	<!-- List list = new ArrayList(); -->
//	<property name="list">
//		<list>
//			<value>brown</value>
//			<value>sally</value>
//			<value>cony</value>
//		</list>
//	</property>
	@Bean
	public CollectionBean collectionBean() {
		CollectionBean collectionBean = new CollectionBean();
		List<String> list = new ArrayList<>();
		list.add("brown");
		list.add("brown");
		list.add("brown");
		return collectionBean;
	}
//맨위
//	<context:property-placeholder
//	location="classpath:/kr/or/ddit/config/db/dbinfo.properties" />

//<bean id="dbConfig" class="kr.or.ddit.ioc.DbConfig">
//	<property name="driverClassName"
//		value="${jdbc.driverClassName}" />
//	<property name="url" value="${jdbc.url}" />
//	<property name="username" value="${jdbc.username}" />
//	<property name="password" value="${jdbc.password}" />
//</bean>
	@Bean
	public DbConfig dbConfig() {
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDriverClassName(driverClassName);
		dbConfig.setUrl(url);
		dbConfig.setUsername(username);
		dbConfig.setPassword(password);
		return dbConfig;
	}

}
