package com.restaurantreservation.aruaru.service;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.tabletest;

public interface UserService {
	// 회원가입
	public int insertUser(User_member member);
	
	// 오라클 연결 테스트용 입니다.
	public int insertTable(tabletest test);
}