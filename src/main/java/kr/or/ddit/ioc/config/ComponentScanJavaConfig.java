package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(basePackageClasses = {클래스명.class}) 이경우는 해당 클래스가 속한 패키지로 스캔한다.
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponentScanJavaConfig {

	//<context:component-scan	base-package="kr.or.ddit"></context:component-scan>
	
	
	
	
}
