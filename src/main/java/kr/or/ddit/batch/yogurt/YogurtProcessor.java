package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	// 여러개의 파라미터를 가져올수 있다.
	// 조회된 파라미터중 dt의 값을 가져온다.
	@Value("#{jobParameters[dt]}")
	private Date dt;

	//@Override
	//public List<DailyVo> process(CycleVo item) throws Exception {
	// CycleVo :
	// dt: 202102, item : cid-1, pid-100, day-2, cnt-1
	// ==>
	// DailyVo :
	// cid-1, pid-100, dt-20210201, cnt-1
	// cid-1, pid-100, dt-20210208, cnt-1
	// cid-1, pid-100, dt-20210215, cnt-1
	// cid-1, pid-100, dt-20210222, cnt-1
	// 2월1일부터 28일까지 loop를 돌아야한다.
	// if(요일= item.요일과 같은지 체크)

//////////////////////////////////////////////////////////////////
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String dt_str = sdf.format(dt);
//		int dt_year = Integer.parseInt(dt_str.substring(0, 3));
//		int dt_month = Integer.parseInt(dt_str.substring(4, 5));
//		int dt_day = Integer.parseInt(dt_str.substring(5, 6));
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(dt);//   
//		calendar.set(dt_year,dt_month, dt_day);// 월은 -1 해주어야 해당월로 인식
//		int dt_day2 = Calendar.DAY_OF_WEEK;
// 		요일 체크
//			if(dt_day2 == item.getDAY()) {}
//////////////////////////////////////////////////////////////////
	// 해당 일로 일시적 데이터를 생성

//		// 현재 날짜 시간
//		Calendar calendar = Calendar.getInstance(); // 현재 날짜 시간이 리턴 됨.
//		// 해당 년월의 마지막 날짜 구하기 (date)
//		calendar.setTime(dt);
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//		// 20210228 02:00:00
//		Date endDt = calendar.getTime();
//
//		// 해당년월의 첫번째 날짜 -1 (date)
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		// 20210201 00:00:00.xx
//		// Date startDt = calendar.getTime();
//		// while(endDt.compareTo(startDt) >0 )
//
//		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
//
//		while (endDt.compareTo(calendar.getTime()) > 0) {
//			if (item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) {
//				// cid,pid,dt(yyyyMMdd),cnt
//				dailyVoList
//						.add(new DailyVo(item.getCid(), item.getPid(), sdf.format(calendar.getTime()), item.getCnt()));
//			}
//		}
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
//		return dailyVoList;
//	}
//}
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {

		// ���� ��¥ �ð�
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dt);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDt = calendar.getTime();
		// 20210228 02:00:00

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);

		// 20210201 00:00:00.5
		// Date startDt = calendar.getTime();

		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		while (endDt.compareTo(calendar.getTime()) > 0) {

			// 20210201 == > �ְ�����
			if (item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) {
				// cid, pid, dt(yyyyMMdd), cnt
				dailyVoList
						.add(new DailyVo(item.getCid(), item.getPid(), sdf.format(calendar.getTime()), item.getCnt()));
			}

			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		}

		return dailyVoList;
	}

}
