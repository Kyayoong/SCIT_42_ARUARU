package com.restaurantreservation.aruaru.service;

import com.restaurantreservation.aruaru.domain.User_member;

public interface MemberService {
	// 회원가입
	public int insertUser(User_member member);
}
