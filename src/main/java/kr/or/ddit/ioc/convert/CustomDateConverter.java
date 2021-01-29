package kr.or.ddit.ioc.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date> {

	private String dateFormat;

	// source : 2021-01-11, yyyy-mm-dd
	@Override
	public Date convert(String source) {
		// 문자를 날짜로 만드는 방법
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// dateFormat setter 설정
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}
