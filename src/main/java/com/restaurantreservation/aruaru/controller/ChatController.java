package com.restaurantreservation.aruaru.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	@GetMapping("chat")
	public ModelAndView chat(Model m) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		try {
			BufferedReader in = new BufferedReader(new FileReader("E:\\workspace\\Test_SpringBoot\\file.txt"));
			String text = "";
			String a1;
			try {
				while ((a1 = in.readLine()) != null) {
				  StringBuffer sb = new StringBuffer(a1);
				 text+=sb.toString()+"\n";
				 m.addAttribute("chatDetail",text);
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
