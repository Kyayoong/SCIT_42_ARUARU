package com.restaurantreservation.aruaru.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurantreservation.aruaru.domain.Admin_Graphs;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.HomeService;
import com.restaurantreservation.aruaru.service.RestaurantService;
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
	
	@Autowired
	RestaurantService rservice;
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	String tags;
	String[] mytags;
	
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
			tags = service.ownTags(user.getUsername());
			if(tags==null) {
				tags = "없음";
			}
			mytags = tags.split("/");
			List<Integer> a = service.recommend(mytags);
			int[] stores = a.stream().mapToInt(i->i).toArray();
			List<Restaurant_member> restaurants = service.recommendStores(stores);
			List<Restaurant_member> byrank = rservice.showByRank();
			List<Restaurant_member> byregdate = rservice.showByRegDate();
			model.addAttribute("byRegDate", byregdate);
			model.addAttribute("byRank", byrank);
			System.out.println(restaurants);
			model.addAttribute("recommend", restaurants);
			
		} else {
			List<Restaurant_member> byrank = rservice.showByRank();
			List<Restaurant_member> byregdate = rservice.showByRegDate();
			model.addAttribute("byRegDate", byregdate);
			model.addAttribute("byRank", byrank);
			model.addAttribute("member_nickname", null);
		}

		return "home";
	}
	
	@GetMapping("resdis")
	public String resdis(int restaurant_num, Model model, HttpServletResponse response) {
		//전달된 글 번호로 글 정보 조회
		Restaurant_member member = rservice.selectOne1(restaurant_num);
		
		ArrayList<Restaurant_file> fileList = rservice.fileselect(restaurant_num);
		
		
		//원래의 파일명
		String originalfile = new String(fileList.get(0).getRestaurant_originalfile());
		
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + fileList.get(0).getRestaurant_savedfile();
		
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
}
