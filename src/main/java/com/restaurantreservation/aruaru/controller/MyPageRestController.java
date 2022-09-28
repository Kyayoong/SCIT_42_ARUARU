package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("mypage")
@Controller
public class MyPageRestController {
	@Autowired
	UserService service;

	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

}
