package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			model.addAttribute("member", member);
		} else {
			model.addAttribute("member_nickname", null);
		}

		return "userView/mypage";
	}

	// 리뷰 작성 화면
	@GetMapping("review")
	public String review(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/review";
	}

	@GetMapping("introduce_store")
	public String introduceStore(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/introduce_store";
	}

	@GetMapping("couponandinquiry")
	public String couponandinqury(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/couponandinquiry";
	}

	@GetMapping("seereservation")
	public String seeresevation(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/seereservation";
	}

	@GetMapping("notice")
	public String notice(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/notice";
	}

	@GetMapping("mywishlist")
	public String mywishlist(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/mywishlist";
	}

	// 회원정보변경 화면으로 이동
	@GetMapping("myinfomodify")
	public String myinfomodify(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/myinfomodify";
	}

	// 회원정보 수정 처리
	@PostMapping("myinfomodify")
	public String myinfomodify(User_member member, @AuthenticationPrincipal UserDetails user) {
		log.debug("수정할 정보 : {}", member);
		member.setMember_id(user.getUsername());
		int result = service.updateUser(member);
		return "redirect:/";
	}

	@GetMapping("mybenefit")
	public String mybenefit(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("멤버 정보:{}", member);
		}

		return "userView/mybenefit";
	}
	
	@GetMapping("insertReview")
	public String insertReview() {
		return "userView/insertReview";
	}
	
	@GetMapping("leaveId")
	public String leaveId(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		
		return "userView/leaveId";
	}
	
	@PostMapping("/leaveId")
	public String leaveId(@AuthenticationPrincipal UserDetails user) {
		User_member member = service.selectUser(user.getUsername());
		int result = service.deleteUser(member.getMember_id());
		return "redirect:/logout";
	}
	
	
	@GetMapping("restaurantMain")
	public String restaurantMain() {
		return "/restaurantView/restaurantMain";
	}
	
	//restMemberMain - 식당멤버관리창
	@GetMapping("restaurantRTMemberMain")
	public String restaurantRTMemberMain() {
		return "/restaurantView/restaurantRTMemberMain";
	}
	
	//genMemberMain - 일반회원관리창
	@GetMapping("restaurantGNMemberMain")
	public String restaurantGNMemberMain() {
		return "/restaurantView/restaurantGNMemberMain";
	}
	
	//boardMain - 게시글관리창
	@GetMapping("restaurantBoardMain")
	public String restaurantBoardMain() {
		return "/restaurantView/restaurantBoardMain";
	}
}
