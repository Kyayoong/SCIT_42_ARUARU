package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("restaurant")
@Controller
public class RestaurantController {
	
	//식당이 등록되어있는지 확인하는 창
	@GetMapping("registCheck")
	public String registCheck() {
		
		
		return "/registView/registCheck";
	}
	
	//식당 등록 폼으로 이동 회원가입창에서 회원가입이 완료되면 넘어갑니다.
	@GetMapping("regist")
	public String regist() {
		
		
		return "/registView/regist";
	}
	
	

}
 