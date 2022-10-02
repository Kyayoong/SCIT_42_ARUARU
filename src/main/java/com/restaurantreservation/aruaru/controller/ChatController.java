package com.restaurantreservation.aruaru.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ChatController {
	@Autowired
	UserService service;
	
	@GetMapping("/chat")
	public ModelAndView chat(Model m, @AuthenticationPrincipal UserDetails user) {
		log.debug("1");
		ModelAndView mv = new ModelAndView();
		log.debug("2");
		mv.setViewName("chat");
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\upload\\file.txt"));
			String text = "";
			String a1;
			log.debug("3");
			try {
				while ((a1 = in.readLine()) != null) {
				  StringBuffer sb = new StringBuffer(a1);
				 text+=sb.toString()+"\n";
				 m.addAttribute("chatDetail", text);
				 log.debug("4");
				 if(user != null) {
					 log.debug("5");
					 User_member member = service.selectUser(user.getUsername());
					 log.debug("6");
					 m.addAttribute("chatName",member.getMember_id());
					 log.debug("7");
					 log.debug("{}", member.getMember_id());
					 
				 }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	
	
	
}
