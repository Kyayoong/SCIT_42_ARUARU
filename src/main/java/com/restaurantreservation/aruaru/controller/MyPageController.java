package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("mypage")
@Controller
public class MyPageController {
	//마이페이지 메인화면
	@GetMapping("")
	public String mypage() {
		return "mypage";
	}
	
	//리뷰 작성 화면
	@GetMapping("review")
	public String review() {
		return "generalMemView/review";
	}
}
