package com.restaurantreservation.aruaru.service;

import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;

public interface RestaurantService {
	
	public Restaurant_member selectOne(String Member_id);
	
	public int regist1(Restaurant_member member);
	
	public int fileregist(Restaurant_file file);
	
	

}
