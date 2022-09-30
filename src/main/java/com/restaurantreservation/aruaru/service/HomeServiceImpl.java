package com.restaurantreservation.aruaru.service;

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
}
