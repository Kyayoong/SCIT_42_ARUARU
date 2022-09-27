package com.restaurantreservation.aruaru.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("mypage")
@Controller
public class MyPageController {
	@Autowired
	UserService service;
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	// 마이페이지 메인화면
	@GetMapping("/")
	public String mypage(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		} else {
			model.addAttribute("member_nickname", null);
		}

		return "userView/mypage";
	}

	// 리뷰 작성 화면
	@GetMapping("review")
	public String review(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/review";
	}

	@GetMapping("introduce_store")
	public String introduceStore(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/introduce_store";
	}

	@GetMapping("couponandinquiry")
	public String couponandinqury(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/couponandinquiry";
	}

	@GetMapping("seereservation")
	public String seeresevation(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/seereservation";
	}

	@GetMapping("notice")
	public String notice(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/notice";
	}

	@GetMapping("mywishlist")
	public String mywishlist(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/mywishlist";
	}

	@GetMapping("profile")
	public String profile(int member_num, Model model, HttpServletResponse response, @AuthenticationPrincipal UserDetails user) {
		//전달된 글 번호로 글 정보 조회
		User_member member = service.selectUser(user.getUsername());
		
		//원래의 파일명
		String originalfile = new String(member.getMember_originalfile());
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + member.getMember_savedfile();
		
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}	
	
	// 회원정보변경 화면으로 이동
	@GetMapping("myinfomodify")
	public String myinfomodify(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/myinfomodify";
	}

	// 회원정보 수정 처리
	@PostMapping("myinfomodify")
	public String myinfomodify(User_member member, @AuthenticationPrincipal UserDetails user) {
		log.debug("수정할 정보 : {}", member);
		member.setMember_id(user.getUsername());
		int result = service.updateUser(member);
		return "redirect:/";
	}

	@GetMapping("mybenefit")
	public String mybenefit(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("멤버 정보:{}", member);
		}

		return "userView/mybenefit";
	}
	
	//리뷰 입력
	@GetMapping("insertReview")
	public String insertReview() {
		return "userView/insertReview";
	}
	
	@GetMapping("leaveId")
	public String leaveId(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		
		return "userView/leaveId";
	}
	
	@PostMapping("/leaveId")
	public String leaveId(@AuthenticationPrincipal UserDetails user) {
		User_member member = service.selectUser(user.getUsername());
		int result = service.deleteUser(member.getMember_id());
		return "redirect:/logout";
	}
	
	
	@GetMapping("restaurantMain")
	public String restaurantMain() {
		return "/restaurantView/restaurantMain";
	}
	
	//restMemberMain - 식당멤버관리창
	@GetMapping("restaurantRTMemberMain")
	public String restaurantRTMemberMain() {
		return "/restaurantView/restaurantRTMemberMain";
	}
	
	//genMemberMain - 일반회원관리창
	@GetMapping("restaurantGNMemberMain")
	public String restaurantGNMemberMain() {
		return "/restaurantView/restaurantGNMemberMain";
	}
	
	//boardMain - 게시글관리창
	@GetMapping("restaurantBoardMain")
	public String restaurantBoardMain() {
		return "/restaurantView/restaurantBoardMain";
	}
	
	@GetMapping("inquiryBoard")
	public String inquiryboard(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		List<Web_board> writtenByme = service.findBoard(user.getUsername());
		model.addAttribute("boardList", writtenByme);
		return "/userView/inquiryBoard";
	}
	
	
	@GetMapping("inquirywrite") 
	public String inquirywrite(Model model, @AuthenticationPrincipal UserDetails user) {
		if(user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "/userView/inquiryWrite";
	}
	
	@PostMapping("submitWebBoard") 
	public String submitWebBoard(Web_board b) {
		log.debug("{}", b);
		int result = service.insertBoard(b);
		return "redirect:/userView/inquiryBoard";
	}
}
