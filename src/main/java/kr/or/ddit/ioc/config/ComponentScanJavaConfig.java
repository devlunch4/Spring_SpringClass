package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(basePackageClasses = {Ŭ������.class}) �̰��� �ش� Ŭ������ ���� ��Ű���� ��ĵ�Ѵ�.
@ComponentScan(basePackages = {"kr.or.ddit"})
@Configuration
public class ComponentScanJavaConfig {

	//<context:component-scan	base-package="kr.or.ddit"></context:component-scan>
	
	
	
	
}
