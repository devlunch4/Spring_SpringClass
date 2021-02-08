package kr.or.ddit.batch.ranger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class RangerReader implements ItemReader<String> {
	private static final Logger logger = LoggerFactory.getLogger(RangerReader.class);
	// 선언
	private List<String> rangers;
	private int index = 0;

	// 생성자
	public RangerReader() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// 누군가 또 read시 순차적으로 인덱스 증가
		// 더이상 읽을 데이터가 없다고 알려주는 방법 : null return.
		if (rangers.size() > index) {
			String ranger = rangers.get(index++);
			logger.debug("Reader: {}", ranger);
			return ranger;
		} else {
			return null;
		}
	}
}
