package com.restaurantreservation.aruaru.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurantreservation.aruaru.domain.Admin_Graphs;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.HomeService;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	// 오라클 연결 테스트용
	@Autowired
	UserService service;
	
	@Autowired
	HomeService homeService;
	
	//쿠키에 방문자 여부 저장
	@GetMapping({ "", "/" })
	public String home(Model model, 
					@AuthenticationPrincipal UserDetails user,
					HttpServletResponse response,
					@CookieValue(name="visited", defaultValue="0") String cookie) {
		
		//방문 시, 쿠키를 확인
		int visited = homeService.checkCookie(response, cookie);
		log.debug("쿠키 성공여부 1-이미방문, 0-첫방문 : {}", visited);
		
		//일일 첫방문일 경우 오늘의 데이터 저장소가 있는지 확인,
		//데이터 저장소가 없으면 생성 후 방문자 추이 +1 (쿠키수명 2시간)
		//저장소가 있으면 데이터 저장소의 방문자 추이 +1 (쿠키수명 2시간)
		if(visited == 0) {
			//방문자 수만 올려주는 객체
			Admin_Graphs data = new Admin_Graphs(0, 0, 1);
			//오늘날자의 데이터저장소가 있는지 확인후 없으면 생성해줌
			int result = homeService.checkNewDate();
			//객체를 전달하여 올릴 데이터만 +1해줌 (여기선 방문자만 +1)
			int increseCnt = homeService.increaseToday(data);
		}
		
		if(user != null) {
		User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		else {
			model.addAttribute("member_nickname", null);
		}

		return "home";
	}
}
