package com.restaurantreservation.aruaru.dao;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;

@Mapper
public interface RestaurantDao {
	
	Restaurant_member selectOne(String Member_id);
	
	int regist1(Restaurant_member member);
	
	int fileregist(Restaurant_file file);
	
	
	

}
