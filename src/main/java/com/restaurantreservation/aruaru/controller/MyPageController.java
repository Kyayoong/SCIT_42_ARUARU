package com.restaurantreservation.aruaru.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;

import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("mypage")
@Controller
public class MyPageController {
	@Autowired
	UserService service;
	@Autowired
	RestaurantService restaurantService;

	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	// 마이페이지 메인화면
	@GetMapping("/")
	public String mypage(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("마이페이지_member:{}", member);
			ArrayList<Reservation> reservationlist = service.seeAllReservation(user.getUsername());
			log.debug("리스트에여 : {}", reservationlist);
			model.addAttribute("reservationlist", reservationlist);
		} else {
			model.addAttribute("member_nickname", "없음");
		}

		return "userView/mypage";
	}

	// 예약내역->리뷰선택창
	@GetMapping("review")
	public String review(Model model, @AuthenticationPrincipal UserDetails user) {
		// 계정정보를 통해 해당 아이디를 가진 이용내역을 다 가져온다.(실제로 간 기록이 있는 경우의 데이터만)
		// 모델에 담아 html에 가져간다.
		if (user == null) {
			return "redirect:/";
		}
		User_member member = service.selectUser(user.getUsername());

		ArrayList<Usage_history> usageList = service.selectAllUsageHistory(user.getUsername());
		// 식당 번호를 통해 식당이름을 가져와서 각 이용내역 객체에 식당 이름 저장.
		for(int i = 0; i < usageList.size(); i++) {
			Restaurant_member restaurantMember = restaurantService.selectOne1(usageList.get(i).getRestaurant_num());
			usageList.get(i).setRestaurant_name(restaurantMember.getRestaurant_name());
		}

		model.addAttribute("member", member);
		model.addAttribute("usageList", usageList);

		return "userView/review";
	}

	// 리뷰 입력창
	@GetMapping("insertReview")
	public String insertReview(int usageNum, Model model) {
		// 해당 번호의 이용 내역 받아오기
		Usage_history usage = service.selectOneUsageHistory(usageNum);
		log.debug(" {} ", usage);
		model.addAttribute("usage", usage);
		return "userView/insertReview";
	}

	// 리뷰입력 form
	@PostMapping("insertReview")
	public String insertReview(int grade, String member_id, int usage_num, int restaurant_num, String restaurant_name,
			String contents) {
		log.debug("{}", grade);
		log.debug(member_id);
		log.debug("{}", usage_num);
		log.debug("{}", restaurant_num);
		log.debug(restaurant_name);
		log.debug(contents);

		String title = "아무 제목";

		Review review = new Review(member_id, restaurant_num, usage_num, title, contents, grade);

		// 새로운 리뷰 객체를 저장한다.
		int result = service.insertReview(review);

		return "redirect:/mypage/review";

//		int review_num;
//	    String member_id;
//	    int restaurant_num;								
//	    int usage_num;
//	    String title;
//	    String contents;
//	    int grade;
	}

	// 가게 소개 페이지
	@GetMapping("introduce_store")
	public String introduceStore(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/introduce_store";
	}

	// 쿠폰
	@GetMapping("couponandinquiry")
	public String couponandinqury(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/couponandinquiry";
	}

	// 이용내역확인
	@GetMapping("seereservation")
	public String seeresevation(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			ArrayList<Reservation> reservationlist = service.seeAllReservation(user.getUsername());
			log.debug("리스트에여 : {}", reservationlist);
			model.addAttribute("reservationlist", reservationlist);
			ArrayList<Reservation> lastreservationlist = service.seeAllLastReservation(user.getUsername());
			model.addAttribute("lastreservationlist", lastreservationlist);
		}
		return "userView/seereservation";
	}

	// 공지사항
	@GetMapping("notice")
	public String notice(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/notice";
	}

	// 찜
	@GetMapping("mywishlist")
	public String mywishlist(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/mywishlist";
	}

	// 프로필 사진 불러오기
	@GetMapping("profile")
	public String profile(int member_num, Model model, HttpServletResponse response,
			@AuthenticationPrincipal UserDetails user) {

		log.debug("profile_member_num: {}", member_num);
		log.debug("profile_response: {}", response);

		// 전달된 글 번호로 글 정보 조회
		User_member member = service.selectUser(user.getUsername());

		// 원래의 파일명
		String originalfile = new String(member.getMember_originalfile());
		try {
			response.setHeader("Content-Disposition",
					" attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 저장된 파일 경로
		String fullPath = uploadPath + "/" + member.getMember_savedfile();

		// 서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;

		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();

			// Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);

			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "home";
//		return "redirect";
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
	public String myinfomodify(User_member member, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
		log.debug("수정할 정보 : {}", member);

		/*
		 * User_member oldmember = null; String oldSavedfile = null; String savedfile =
		 * null;
		 * 
		 * //첨부파일이 있는 경우 기존파일 삭제 후 새 파일 저장 if (upload != null && !upload.isEmpty()) {
		 * oldmember = service.selectUser(user.getUsername()); log.debug("1");
		 * oldSavedfile = oldmember == null ? null : oldmember.getMember_savedfile();
		 * log.debug("1");
		 * 
		 * savedfile = FileService.saveFile(upload, uploadPath); log.debug("1");
		 * member.setMember_originalfile(upload.getOriginalFilename()); log.debug("1");
		 * member.setMember_savedfile(savedfile); log.debug("새파일:{}, 구파일:{}", savedfile,
		 * oldSavedfile); }
		 */
		member.setMember_id(user.getUsername());
		int result = service.updateUser(member);

		// 글 수정 성공 and 첨부된 파일이 있는 경우 파일도 삭제
		/*
		 * if (result == 1 && savedfile != null) { FileService.deleteFile(uploadPath +
		 * "/" + oldSavedfile); }
		 */

		return "redirect:";
	}

	// 회원혜택
	@GetMapping("mybenefit")
	public String mybenefit(Model model, @AuthenticationPrincipal UserDetails user) {

		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("멤버 정보:{}", member);
		}

		return "userView/mybenefit";
	}

	// 회원탈퇴
	@GetMapping("leaveId")
	public String leaveId(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}

		return "userView/leaveId";
	}

	// 회원탈퇴
	@PostMapping("/leaveId")
	public String leaveId(@AuthenticationPrincipal UserDetails user) {
		User_member member = service.selectUser(user.getUsername());
		int result = service.deleteUser(member.getMember_id());
		return "redirect:/logout";
	}

	// 식당메인화면
	@GetMapping("restaurantMain")
	public String restaurantMain() {
		return "/restaurantView/restaurantMain";
	}

	// restMemberMain - 식당관리화면
	@GetMapping("restaurantRTMemberMain")
	public String restaurantRTMemberMain(Model model,@AuthenticationPrincipal UserDetails user) {
		
		Restaurant_member member = restaurantService.selectOne(user.getUsername());
		ArrayList<Reservation> reservationList = restaurantService.ReservationList(member.getRestaurant_num());
		log.debug("{}", reservationList);

		model.addAttribute("reservationList", reservationList);

		return "/restaurantView/restaurantRTMemberMain";

	}

	// rsetreview - 리뷰관리
	@GetMapping("rsetreview")
	public String rsetreview() {
		return "/restaurantView/rsetreview";
	}

	@GetMapping("inquiryboard")
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
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "/userView/inquiryWrite";
	}

	@GetMapping("inquiryread")
	public String inquiryread(int board_num, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("{}", board_num);
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		Web_board b = service.readBoard(board_num);
		model.addAttribute("board", b);
		List<Web_reply> replyListAll = service.readReplyAll(board_num);
		model.addAttribute("replyList", replyListAll);
		// 넘어온 게시글 번호를 통해 sql에서 게시글 객체 찾기
		// 찾아온 놈을 모델에 넣고
		// inputiryboard.html로 가져간다.

		return "/userView/inquiryRead";
	}

	@PostMapping("submitWebBoard")
	public String submitWebBoard(Web_board b) {
		log.debug("{}", b);
		int result = service.insertBoard(b);
		return "redirect:/mypage/inquiryboard";
	}

	@ResponseBody
	@PostMapping("replyInsert")
	public String replyinsert(Web_reply r) {
		log.debug("{}", r);
		int result = service.insertReply(r);
		return "redirect:/userView/inquiryRead";
	}

	@ResponseBody
	@GetMapping("replyList")
	public List<Web_reply> replyList(int board_num) {
		log.debug("{}", board_num);
		List<Web_reply> replyList = service.readReply(board_num);
		return replyList;
	}

	@ResponseBody
	@GetMapping("replyDelete")
	public int replyDelete(int reply_num) {
		log.debug("{}", reply_num);
		int result = service.replyDelete(reply_num);
		return result;
	}

	@GetMapping("inquiryupdate")
	public String inquiryupdate(int board_num, Model m, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			m.addAttribute("member", member);
		}
		log.debug("{}", board_num);
		Web_board b = service.readBoard(board_num);
		m.addAttribute("board", b);
		return "userView/inquiryModify";
	}

	@PostMapping("inquirymodifyAction")
	public String inquirymodifyAction(Web_board b, Model m) {

		log.debug("{}", b);
		int result = service.updateBoard(b);
		return "redirect:/userView/inquiryRead";
	}
}
