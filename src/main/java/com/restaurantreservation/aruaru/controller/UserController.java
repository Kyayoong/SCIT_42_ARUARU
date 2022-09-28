package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.dao.UserDao;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;
import com.restaurantreservation.aruaru.util.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService service;

	/**
	 * 게시판 첨부파일 업로드 경로
	 */
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	@Autowired
	RestaurantService service1;

	// 로그인 페이지
	@GetMapping("login")
	public String login() {
		return "/registView/loginForm";
	}

	// 회원가입시 일반사용자, 식당 구분
	@GetMapping("/joinselect")
	public String joinselect() {
		return "/registView/joinselect";
	}

	// 일반 사용자로 회원가입
	@GetMapping("/join_as_user")
	public String join_as_user(Model model) {
		ArrayList<Tags> tagList = service1.tagList("맛");
		ArrayList<Tags> tagList2 = service1.tagList("서비스");
		ArrayList<Tags> tagList3 = service1.tagList("인기");
		ArrayList<Tags> tagList4 = service1.tagList("가격");
		ArrayList<Tags> tagList5 = service1.tagList("계절");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		return "/registView/join_as_user";
	}

	// 회원가입 처리
	@PostMapping("/insert_user")
	public String insertUser(User_member member, MultipartFile upload) {
		log.debug("회원정보 : {}", upload);
		if (!upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			member.setMember_originalfile(upload.getOriginalFilename());
			member.setMember_savedfile(savedfile);
		}

		log.debug("회원정보 : {}", member);
		int result = service.insertUser(member);
		log.debug("회원정보 : {}", result);
		return "redirect:/";
	}

	// 아이디 중복체크
	@GetMapping("idcheck")
	public String idcheck() {
		return "/registView/idForm";
	}

	// 아이디 중복체크 처리
	@PostMapping("idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("검색할 ID : {}", searchId);
		boolean result = service.idcheck(searchId);

		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);

		return "/registView/idForm";
	}

	// 이메일 인증
	@GetMapping("emailcheck")
	public String emailcheck() {
		return "/registView/emailForm";
	}

	// 식당 측 회원가입
	@GetMapping("/join_as_restaurant")
	public String join_as_restaurant() {
		return "/registView/join_as_restaurant";
	}

	// ID 찾기, PW 찾기 선택 화면
	@GetMapping("inquirySelect")
	public String inquirySelect() {
		return "registView/inquirySelect";
	}

	// ID 찾기
	@GetMapping("idInquiry")
	public String idInquiry() {
		return "registView/idInquiry";
	}

	// PW 찾기
	@GetMapping("pwInquiry")
	public String pwInquiry() {
		return "registView/pwInquiry";
	}

}
