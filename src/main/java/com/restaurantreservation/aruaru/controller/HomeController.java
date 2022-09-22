package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.tabletest;
import com.restaurantreservation.aruaru.service.UserService;

@Controller
public class HomeController {
	// 오라클 연결 테스트용
	@Autowired
	UserService service;

	@GetMapping({ "", "/" })
	public String home(Model model, @AuthenticationPrincipal UserDetails user) {
		
		if(user != null) {
		User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		else {
			model.addAttribute("member_nickname", null);
		}

		return "home";
	}

	/*
	 * //오라클 연결 테스트용
	 * 
	 * @PostMapping("insertTest") public String insertTest(String tableid, String
	 * text) { tabletest test = new tabletest(tableid, text); int result =
	 * service.insertTable(test); return "home"; }
	 */
}
