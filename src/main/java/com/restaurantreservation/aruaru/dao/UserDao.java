package com.restaurantreservation.aruaru.dao;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.tabletest;

@Mapper
public interface UserDao {
	// 회원가입
	int insertUser(User_member member);

	// ID 개수 확인
	public int countMemberid(String member_id);

	// 정보 가져오기
	User_member selectOne(String member_id);

	// 정보 수정
	public int updateUser(User_member member);
}
