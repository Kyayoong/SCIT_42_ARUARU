package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("joinselect")
	public String joinselect() {
		return "joinselect";
	}
	
	@GetMapping("join_as_user") 
	public String join_as_user() {
		return "join_as_user";
	}
	
	@GetMapping("join_as_restaurant") 
	public String join_as_restaurant() {
		return "join_as_restaurant";
	}
}

