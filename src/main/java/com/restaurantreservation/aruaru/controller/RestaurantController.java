package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;
import com.restaurantreservation.aruaru.util.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("restaurant")
@Controller
public class RestaurantController {

	/**
	 * 게시판 첨부파일 업로드 경로
	 */
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	@Autowired
	RestaurantService service;
	
	@Autowired
	UserService memberService;

	/**
	 * 식당이 등록되어있는지 확인하는 창
	 */
	@GetMapping("/join_as_restaurantCheck")
	public String join_as_restaurantCheck() {
		return "/registView/join_as_restaurantCheck";
	}

	/**
	 * 식당 등록 폼으로 이동 회원가입창에서 회원가입이 완료되면 넘어갑니다.
	 */
	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {

		return "/registView/join_as_restaurant";
	}

	/**
	 * 평균가격이랑 메뉴추가화면입니다.
	 */
	@GetMapping("/join_as_restaurant_menu")
	public String join_as_restaurant_menu(Model model) {

		ArrayList<Tags> tagList = service.tagList("맛");
		ArrayList<Tags> tagList2 = service.tagList("서비스");
		ArrayList<Tags> tagList3 = service.tagList("인기");
		ArrayList<Tags> tagList4 = service.tagList("가격");
		ArrayList<Tags> tagList5 = service.tagList("계절");
		ArrayList<Tags> tagList6 = service.tagList("분위기");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);

		return "registView/join_as_restaurant_menu";
	}

	/**
	 * 등록창입니다. ajax는 아직고민중입니다.
	 */
	@PostMapping("regist1")
	public String regist1(ArrayList<MultipartFile> upload, Restaurant_member member,
			@AuthenticationPrincipal UserDetails user, Restaurant_file file) {
		log.debug("{}", member);
		log.debug("파일 업로드 경로: {}", uploadPath);
		log.debug("파일 정보: {}", upload);

		member.setMember_id(user.getUsername());

		int result = service.regist1(member);

		Restaurant_member member2 = service.selectOne(user.getUsername());

		for (int i = 0; i < upload.size(); i++) {

			if (!upload.isEmpty()) {

				String savedfile = FileService.saveFile(upload.get(i), uploadPath);
				file.setRestaurant_num(member2.getRestaurant_num());
				file.setRestaurant_originalfile(upload.get(i).getOriginalFilename());
				file.setRestaurant_savedfile(savedfile);

				int result2 = service.fileregist(file);
				log.debug("결과 : {}", result2);
			}

			log.debug("결과 : {}", result);

		}
		return "redirect:/restaurant/join_as_restaurant_menu";
	}

	/**
	 * 메뉴 등록화면으로 이동합니다~
	 */
	@GetMapping("/join_as_restaurant_menu_add")
	public String idcheck() {
		return "/registView/join_as_restaurant_menu_add";
	}

	// 평균가격가 업데이트
	@PostMapping("regist2")
	public String regist2(Restaurant_member member, @AuthenticationPrincipal UserDetails user) {

		Restaurant_member member2 = service.selectOne(user.getUsername());
		member2.setMenu_priceavr(member.getMenu_priceavr());

		int result = service.Rupdate(member2);

		log.debug("결과 : {}", result);

		return "redirect:/";
	}
	
	@GetMapping("deleteRest")
	public String deleteRest(int restaurant_num,@AuthenticationPrincipal UserDetails user) {
		
		int result = service.deleteRest(restaurant_num);
		int result1 = memberService.updateRole(user.getUsername());
		log.debug("result : {}",result);
		
		return "redirect:/";
	}
}
