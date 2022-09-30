package com.restaurantreservation.aruaru.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;

import com.restaurantreservation.aruaru.domain.Admin_Graphs;

public interface HomeService {
	/**
	 * 홈 방문 시, 쿠키 확인하여 없으면 방문자수를 올려준다.
	 * @param response:접속 브라우저에 대한 인수, cookie: 체크할 쿠키 내용
	 * @return 1=있음, 0=없음 
	 */
	int checkCookie(HttpServletResponse response, String cookie);
	
	/**
	 * 일일 추이 데이터의 저장값을 +1해주는 메소드
	 * @param data 일일 그래프 객체. +되는 사항들만 1을 입력. ex)방문자수만 올릴 경우, 0, 0, 0, 0, 1 저장.
	 * @return 저장 성공 여부
	 */
	int increaseToday(Admin_Graphs data);
	
	/**
	 * 일일 데이터 기입을 위해, 
	 * sql상에서 date의 연/월/일의 칼럼(오늘날짜-sysdate)이 있는지 없는지 확인.
	 * 없으면 새롭게 칼럼을 생성하여 기입한다.
	 * @return 0:없음
	 */
	int checkNewDate();
}
