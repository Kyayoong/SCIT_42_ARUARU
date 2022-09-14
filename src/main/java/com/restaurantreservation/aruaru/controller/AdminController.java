package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@Controller
public class AdminController {
	
	@GetMapping("main")
	public String main() {
		return "/adminView/adminMain";
	}
	
	//restMemberMain - 식당멤버관리창
	@GetMapping("restMemberMain")
	public String restMemberMain() {
		return "/adminView/adminRTMemberMain";
	}
	
	//genMemberMain - 일반회원관리창
	@GetMapping("genMemberMain")
	public String genMemberMain() {
		return "/adminView/adminGNMemberMain";
	}
	
	//boardMain - 게시글관리창
	@GetMapping("boardMain")
	public String boardMain() {
		return "/adminView/adminBoardMain";
	}
}
