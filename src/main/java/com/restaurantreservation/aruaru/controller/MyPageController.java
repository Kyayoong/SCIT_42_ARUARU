package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mypage")
@Controller
public class MyPageController {
	//마이페이지 메인화면
	@GetMapping("/")
	public String mypage() {
		return "userView/mypage";
	}
	
	//리뷰 작성 화면
	@GetMapping("review")
	public String review() {
		return "userView/review";
	}
	
	@GetMapping("introduce_store")
	public String introduceStore() {
		return "introduce_store";
	}
	
	@GetMapping("couponandinquiry") 
	public String couponandinqury(){
		return "couponandinquiry";
	}
	
	@GetMapping("seereservation") 
	public String seeresevation() {
		return "seereservation";
	}
	
	@GetMapping("notice")
	public String notice() {
		return "notice";
	}
	
	@GetMapping("mywishlist") 
		public String mywishlist() {
		return "userView/mywishlist";
	}
	
	
}
