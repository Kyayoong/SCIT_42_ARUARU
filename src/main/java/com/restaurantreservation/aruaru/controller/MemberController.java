package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@GetMapping("loginForm")
	public String login() {
		return "loginForm";
	}
	
	@GetMapping("joinselect")
	public String joinselect() {
		return "joinselect";
	}
	
	@GetMapping("join_as_user") 
	public String join_as_user() {
		return "join_as_user";
	}
	
	@PostMapping("insert_user")
	public String insertUser(User_member member) {
		log.debug("회원정보 : {}", member);
		int result = service.insertUser(member);
		log.debug("회원정보 : {}", result);
		return "redirect:/";
	}
	
	@GetMapping("join_as_restaurant") 
	public String join_as_restaurant() {
		return "join_as_restaurant";
	}
}

