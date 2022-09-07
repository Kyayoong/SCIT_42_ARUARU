package com.restaurantreservation.aruaru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.RestaurantDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantDao dao;
	
	public int restaurantInsert() {
		int result = dao.restaurantInsert();
		return result;
	}
	
	
	
}
