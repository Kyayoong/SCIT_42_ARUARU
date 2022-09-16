package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.util.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("restaurant")
@Controller
@ResponseBody
public class RestaurantRestController {
	
	/**
	 * 게시판 첨부파일 업로드 경로
	 */
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	RestaurantService service;
	
	@PostMapping("insertmenu")
	public void insertmenu(MultipartFile upload,Menu menu,@AuthenticationPrincipal UserDetails user) {
		log.debug("업로드 {}",upload);
		log.debug("메뉴 {}",menu);
		
		Restaurant_member member = service.selectOne(user.getUsername());
		
		String savedfile = FileService.saveFile(upload, uploadPath);
		menu.setRestaurant_num(member.getRestaurant_num());
		menu.setMenu_originalfile(upload.getOriginalFilename());
		menu.setMenu_savedfile(savedfile);
		menu.setMenu_suggestion(menu.getMenu_suggestion());
		
		int result = service.insertmenu(menu);
		
		log.debug("결과 {}",result);
	}
	
}
