package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurantreservation.aruaru.domain.tabletest;
import com.restaurantreservation.aruaru.service.MemberService;

@Controller
public class HomeController {
	//오라클 연결 테스트용
	@Autowired MemberService service;
	
	@GetMapping({"","/"})
	public String home() {
		return "home";
	}
	
	//오라클 연결 테스트용
	@PostMapping("insertTest")
	public String insertTest(String tableid, String text) {
		tabletest test = new tabletest(tableid, text);
		int result = service.insertTable(test);
		return "home";
	}
}
