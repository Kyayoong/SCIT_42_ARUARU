package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.dao.UserDao;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService service;

	@Autowired
	UserDao dao;

	// 로그인 페이지
	@GetMapping("login")
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

	// 아이디 중복체크
//	@ResponseBody
//	@PostMapping("idcheck")
//	public int check(String member_id) {
//		int cnt = 0;
//		cnt = dao.countMemberid(member_id);
//		return cnt;
//	}

	@GetMapping("idcheck")
	public String idcheck() {
		return "/registView/idForm";
	}

	@GetMapping("emailcheck")
	public String emailcheck() {
		return "/registView/emailForm";
	}

	@PostMapping("idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("검색할 ID : {}", searchId);
		boolean result = service.idcheck(searchId);

		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);

		return "/registView/idForm";
	}

	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {
		return "/registView/join_as_restaurant";
	}

	@GetMapping("inquirySelect")
	public String inquirySelect() {
		return "registView/inquirySelect";
	}

	@GetMapping("idInquiry")
	public String idInquiry() {
		return "registView/idInquiry";
	}

	@GetMapping("pwInquiry")
	public String pwInquiry() {
		return "registView/pwInquiry";
	}

	@GetMapping("myinfomodify")
	public String myinfomodify() {
		return "/userView/myinfomodify";
	}

	@GetMapping("mypage")
	public String mypage() {
		return "/userView/mypage";
	}
}
