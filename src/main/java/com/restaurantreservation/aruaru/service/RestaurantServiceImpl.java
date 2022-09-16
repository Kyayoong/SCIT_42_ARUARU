package com.restaurantreservation.aruaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.RestaurantDao;
import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao dao;

	
	//아이디로 조회하여 식당객체가져오기
	@Override
	public Restaurant_member selectOne(String Member_id) {
		Restaurant_member member = dao.selectOne(Member_id);
		return member;
	}
	//식당회원 저장하기
	@Override
	public int regist1(Restaurant_member member) {
		int result = dao.regist1(member);
		return result;
	}
	//식당파일 저정하기
	@Override
	public int fileregist(Restaurant_file file) {
		int result = dao.fileregist(file);
		return result;
	}
	//메뉴 저장하기
	@Override
	public int insertmenu(Menu menu) {
		int result = dao.insertmenu(menu);
		return result;
	}



}
