package com.restaurantreservation.aruaru.dao;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.User_member;

@Mapper
public interface MemberDao {
	// 회원가입
	int insertUser(User_member member);

}
