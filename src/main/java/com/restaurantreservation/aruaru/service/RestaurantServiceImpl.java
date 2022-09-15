package com.restaurantreservation.aruaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.RestaurantDao;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao dao;

	@Override
	public int regist1(Restaurant_member member) {
		int result = dao.regist1(member);
		return result;
	}

	@Override
	public int fileregist(Restaurant_file file) {
		int result = dao.fileregist(file);
		return result;
	}

	@Override
	public Restaurant_member selectOne(String Member_id) {
		Restaurant_member member = dao.selectOne(Member_id);
		return member;
	}

}
