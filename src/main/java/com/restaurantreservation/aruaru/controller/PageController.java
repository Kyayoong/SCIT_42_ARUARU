package com.restaurantreservation.aruaru.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_time;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("stores")
@Slf4j
@Controller
public class PageController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	RestaurantService service;
	
	@Autowired
	UserService service1;
	
	//식당 검섹 페이지
	@GetMapping("search") 
	public String stores(@RequestParam String s_address
						,@RequestParam String s_tags
						,@RequestParam String s_days
						,@RequestParam String s_time
						,@RequestParam String s_people
						,@RequestParam String s_sector
			,Model model
			,@AuthenticationPrincipal UserDetails user) {
		
		Map<String, String> map = new HashMap<>();
		map.put("s_sector", s_sector);
		map.put("s_address", s_address);
		map.put("s_tags", s_tags);
		map.put("s_days", s_days);
		map.put("s_time", s_time);
		map.put("s_people", s_people);
		
		Restaurant_zzim zzim = service.zzimmselect()
		
		if(user != null) {
			User_member member = service1.selectUser(user.getUsername());
				model.addAttribute("member", member);
			}
			else {
				model.addAttribute("member_nickname", null);
			}
		
		ArrayList<Restaurant_member> resList = service.resListSearch(map);
		log.debug("에에 {}",resList);
		
		
		ArrayList<Tags> tagList = service.tagList("");
		log.debug("tagList {}",tagList);
		model.addAttribute("tagList",tagList);
		model.addAttribute("resList", resList);
		return "stores";
	}
	
	//식당 상세 페이지
	@GetMapping("introduce_store")
	public String introduce_store(int restaurant_num, Model model, @AuthenticationPrincipal UserDetails user) {
		if(user != null) {
			User_member member = service1.selectUser(user.getUsername());
				model.addAttribute("member", member);
			}
			else {
				model.addAttribute("member_nickname", null);
			}
		
		Restaurant_member storeList = service.selectOne1(restaurant_num);
		ArrayList<Menu> menuList = service.menucheck(restaurant_num);
		ArrayList<Restaurant_time> timeTable = service.searchTime(restaurant_num);
		ArrayList<Tags> storeTags = service.searchStoreTags(restaurant_num);
		ArrayList<Restaurant_file> fileList = service.fileselect(restaurant_num);
		log.debug("{}",storeTags);
		model.addAttribute("menuList", menuList);
		model.addAttribute("store", storeList);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("storeTagList", storeTags);
		model.addAttribute("fileList", fileList);
		return "views/introduce_store";
	}
	
	// 예약 폼
		@GetMapping("reservation")
		public String reservatio() {
			
			return "/restaurantView/reservationForm";
		}
	
	/**
	 * 보여주기 
	 * @param filenum 파일번호
	 */
	@GetMapping("filedis")
	public String filedis(int restaurant_file_num, Model model, HttpServletResponse response) {
		//전달된 글 번호로 글 정보 조회
		Restaurant_file file = service.readFile(restaurant_file_num);
		
		//원래의 파일명
		String originalfile = new String(file.getRestaurant_originalfile());
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + file.getRestaurant_savedfile();
		
		
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
