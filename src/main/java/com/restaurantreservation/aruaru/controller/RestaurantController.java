package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("restaurant")
@Controller
public class RestaurantController {
	
	@GetMapping("regist")
	public String regist() {
		
		
		return "/registView/regist";
	}

}
 