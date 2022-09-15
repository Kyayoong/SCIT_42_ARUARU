package com.restaurantreservation.aruaru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("introduce_store")
	public String introduceStore() {
		return "introduce_store";
	}
	
	@GetMapping("couponandinquiry") 
	public String couponandinqury(){
		return "couponandinquiry";
	}
	
	@GetMapping("seereservation") 
	public String seeresevation() {
		return "seereservation";
	}
	
	@GetMapping("notice")
	public String notice() {
		return "notice";
	}

}
