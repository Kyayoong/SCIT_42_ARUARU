package com.restaurantreservation.aruaru.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantreservation.aruaru.dao.HomeDao;
import com.restaurantreservation.aruaru.domain.Admin_Graphs;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeDao dao;
	
	//쿠키를 확인하여 오늘 첫 방문인지 확인하는 메소드
	@Override
	public int checkCookie(HttpServletResponse response, String cookie) {
		int result = 1;
		
		//visited가 0이면 방문이력이 없으니 sql 방문횟수++
		//새로운 visited 생성 후 "1" 저장
		if(cookie.equals("0")) {
			log.debug("처음 방문한 유저입니다.");
			//쿠키 생성 메소드 첫 방문자여서 result를 0으로 세팅한다.
			result = createCookie(response);
		} else if(cookie.equals("true")) {
			log.debug("이미 방문한 유저입니다.");
		}
		return result;
	}
	
	//쿠키 생성 메소드
	private int createCookie(HttpServletResponse response) {
		int result = 0;
		Cookie newCookie = new Cookie("visited", "true");
		//쿠키의 수명 (2시간)
		newCookie.setMaxAge(60*60*2);
		log.debug("쿠키 세팅 정보: {}", newCookie.getValue());
		response.addCookie(newCookie);
		return result;
	}
	
	//DB의 일일 추이 데이터 저장값을 올려주는 메소드
	@Override
	public int increaseToday(Admin_Graphs data) {
		int result = dao.increaseToday(data);
		return result;
	}
	
	//일일 데이터 수치 저장소가 있는지 확인한다. 0:없음 -> 새로 만든다.
	@Override
	public int checkNewDate() {
		int checkResult = dao.checkNewDate();
		log.debug("오늘일자 검색 결과 - service 0이면 새로 생성 : {}", checkResult);
		
		//체크결과가 0이면 새로 생성 후, 생성결과 createResult 반환
		if(checkResult == 0) {
			int createResult = dao.createNewDate();
			log.debug("오늘일자 생성 결과 - service 1-생성성공 : {}", createResult);
			return createResult;
		}
		
		//체크결과가 1이면 체크 결과 리턴
		return checkResult;
	}
	
	//그래프에 기입할 데이터 가져오기
	@Override
	public Admin_Graphs selectData(int i) {
		//데이터 불러오기 (해당 날짜)
		Admin_Graphs graphData = dao.selectData(i);
		
		//날짜 포맷 바꾸기 + 해당 날짜의 리뷰개수 가져오기 
		//+ 해당 날짜의 데이터가 null일 때, 0으로 채워준다.
		Admin_Graphs processedData = changeData(i, graphData);
		
		return processedData;
	}
	
	//불러온 그래프 데이터의 가공
	public Admin_Graphs changeData(int i, Admin_Graphs graphData) {
		int allreview_cnt;
		
		//데이터가 없으면, 날짜에는 해당 날짜 기입후, 모든 데이터에 0 기입
		if(graphData == null) {
			Calendar calendar = new GregorianCalendar();
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
			
			calendar.add(Calendar.DATE, i);		
			String dates = format.format(calendar.getTime());		
			
			Admin_Graphs newGraphData = new Admin_Graphs();
			newGraphData.setDates(dates);
			newGraphData.setUsers_cnt(0);
			newGraphData.setRestaurant_cnt(0);
			
			//일자별 전체 리뷰 수
			allreview_cnt = dao.selectReviewCntByDate(i);
			newGraphData.setAllreview_cnt(allreview_cnt);
			newGraphData.setVisit_cnt(0);
			
			return newGraphData;
		}
		//데이터가 있으면 해당 데이터의 날짜 포멧 변경, 리뷰 개수 불러와서 저장 후 리턴
		//일자 별 전체 리뷰 수
		allreview_cnt = dao.selectReviewCntByDate(i);
		String originalDate = graphData.getDates();
		String newDate = originalDate.substring(2, 10).replace('-', '/');
		graphData.setDates(newDate);
		graphData.setAllreview_cnt(allreview_cnt);
		return graphData;
	}
}
