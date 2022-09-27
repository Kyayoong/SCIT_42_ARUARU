package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.domain.Menu;
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
	
	@GetMapping("search") 
	public String stores(Model model,@AuthenticationPrincipal UserDetails user) {
		if(user != null) {
			User_member member = service1.selectUser(user.getUsername());
				model.addAttribute("member", member);
			}
			else {
				model.addAttribute("member_nickname", null);
			}
		ArrayList<Restaurant_member> resList = service.resList();
		ArrayList<Tags> tagList = service.tagList("");
		log.debug("tagList {}",tagList);
		model.addAttribute("tagList",tagList);
		model.addAttribute("resList", resList);
		return "stores";
	}
	
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
		log.debug("{}",storeTags);
		model.addAttribute("menuList", menuList);
		model.addAttribute("store", storeList);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("storeTagList", storeTags);
		return "views/introduce_store";
	}
	
}
