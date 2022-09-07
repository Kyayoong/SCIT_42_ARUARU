package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;

@RequestMapping("restaurant")
@Controller
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	//식당이 등록되어있는지 확인하는 창
	@GetMapping("/join_as_restaurantCheck")
	public String join_as_restaurantCheck() {
		return "/registView/join_as_restaurantCheck";
	}
	
	//식당 등록 폼으로 이동 회원가입창에서 회원가입이 완료되면 넘어갑니다.	
	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {
		return "/registView/join_as_restaurant";
	}
	
}
 