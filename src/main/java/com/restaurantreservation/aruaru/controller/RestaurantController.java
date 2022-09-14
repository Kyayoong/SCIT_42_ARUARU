package com.restaurantreservation.aruaru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("restaurant")
@Controller
public class RestaurantController {
	
	//게시판 첨부파일 업로드 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@Autowired
	RestaurantService service;
	
	//식당이 등록되어있는지 확인하는 창
	@GetMapping("/join_as_restaurantCheck")
	public String join_as_restaurantCheck() {
		return "/registView/join_as_restaurantCheck";
	}
	
	//식당 등록 폼으로 이동 회원가입창에서 회원가입이 완료되면 넘어갑니다.	
	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {
		return "/registView/join_as_restaurant";
	}
	
	@ResponseBody
	@PostMapping("regist1")
	public int idCheck(List<MultipartFile> upload,Restaurant_member member,
			Tags tag, Restaurant_file file
			) {
		log.debug("{}",member);
		log.debug("파일 업로드 경로: {}", uploadPath);
		log.debug("{}",tag);
		log.debug("{}",file);
		log.debug("파일 정보: {}", upload);
		return 0;
	}
	
}
 