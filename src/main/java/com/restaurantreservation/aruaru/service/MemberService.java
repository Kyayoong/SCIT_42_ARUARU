package com.restaurantreservation.aruaru.service;

import com.restaurantreservation.aruaru.domain.Test;
import com.restaurantreservation.aruaru.domain.User_member;

public interface MemberService {
	public int insertUser(User_member member);

	public int insertTest(Test test);
}
