package com.restaurantreservation.aruaru.dao;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.tabletest;

@Mapper
public interface UserDao {
	// 회원가입
	int insertUser(User_member member);
	
	// 오라클 연결 테스트용입니다.
	int insertTest(tabletest test);

}
