package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.UserService;

@RequestMapping("mypage")
@Controller
public class MyPageController {
	@Autowired
	UserService service;
	
	// 마이페이지 메인화면
	@GetMapping("/")
	public String mypage(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member_nickname", member.getMember_nickname());
		} else {
			model.addAttribute("member_nickname", null);
		}

		return "userView/mypage";
	}

	// 리뷰 작성 화면
	@GetMapping("review")
	public String review() {
		return "userView/review";
	}

	@GetMapping("introduce_store")
	public String introduceStore() {
		return "userView/introduce_store";
	}

	@GetMapping("couponandinquiry")
	public String couponandinqury() {
		return "userView/couponandinquiry";
	}

	@GetMapping("seereservation")
	public String seeresevation() {
		return "userView/seereservation";
	}

	@GetMapping("notice")
	public String notice() {
		return "userView/notice";
	}

	@GetMapping("mywishlist")
	public String mywishlist() {
		return "userView/mywishlist";
	}

	@GetMapping("myinfomodify")
	public String myinfomodify() {
		return "userView/myinfomodify";
	}

	@GetMapping("mybenefit")
	public String mybenefit() {
		return "userView/mybenefit";
	}
	
	@GetMapping("insertReview")
	public String insertReview() {
		return "userView/insertReview";
	}
}
