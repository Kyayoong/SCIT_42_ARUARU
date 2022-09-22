package com.restaurantreservation.aruaru.service;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.tabletest;

public interface UserService {
	// 회원가입
	public int insertUser(User_member member);

	// ID 중복확인
	public boolean idcheck(String member_id);

	//회원 정보 가져옹기
	public User_member selectUser(String member_id);

	// 정보 수정
	public int updateUser(User_member member);
}
