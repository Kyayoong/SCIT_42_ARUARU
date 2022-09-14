package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("introduce_store")
	public String introduceStore() {
		return "introduce_store";
	}
	
	@GetMapping("mypage")
	public String mypage() {
		return "mypage";
	}
}
