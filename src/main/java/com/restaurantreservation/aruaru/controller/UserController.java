package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService service;

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String login() {
		return "/registView/loginForm";
	}

	// 회원가입시 일반사용자, 식당 구분
	@GetMapping("/joinselect")
	public String joinselect() {
		return "/registView/joinselect";
	}

	// 일반 사용자로 회원가입
	@GetMapping("/join_as_user")
	public String join_as_user() {
		return "/registView/join_as_user";
	}

	// 회원가입
	@PostMapping("/insert_user")
	public String insertUser(User_member member) {
		log.debug("회원정보 : {}", member);
		int result = service.insertUser(member);
		log.debug("회원정보 : {}", result);
		return "redirect:/";
	}

	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {
		return "/registView/join_as_restaurant";
	}
}
