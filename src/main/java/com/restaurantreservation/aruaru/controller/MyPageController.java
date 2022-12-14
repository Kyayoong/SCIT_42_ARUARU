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

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_Graphs;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/mypage")
@Controller
public class MyPageController {
	@Autowired
	UserService service;
	@Autowired
	RestaurantService restaurantService;
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	// ??????????????? ????????????
	@GetMapping("/")
	public String mypage(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("???????????????_member:{}", member);
			ArrayList<Reservation> reservationlist = service.seeAllReservation(user.getUsername());
			log.debug("??????????????? : {}", reservationlist);
			model.addAttribute("reservationlist", reservationlist);
			ArrayList<Reservation> cancelReservationList = restaurantService
					.seeAllCancelReservation(user.getUsername());
			model.addAttribute("cancelReservationList", cancelReservationList);
			ArrayList<Restaurant_zzim> mywishlist = service.mywishlist(user.getUsername());
			log.debug("??? : {}", mywishlist);
			model.addAttribute("mywishlist", mywishlist);
			
		} else {
			model.addAttribute("member_nickname", "??????");
		}
		return "userView/mypage";
	}

	// ????????????->???????????????
	@GetMapping("review")
	public String review(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user == null) {
			return "redirect:/";
		}
		User_member member = service.selectUser(user.getUsername());
		// ??????????????? ?????? ?????? ???????????? ?????? ??????????????? ??? ????????????.(????????? ??? ????????? ?????? ????????? ????????????)
		ArrayList<Usage_history> usageList = service.selectAllUsageHistory(user.getUsername());
		// ?????? ???????????? ?????? ????????? ????????????
		ArrayList<Review> reviewList = service.selectAllReview(user.getUsername());
		// UsageHistory?????? num??? ???????????? ?????????????????? ????????????. ?????? ???????????? ?????? ????????? ????????? ????????????
		log.debug("{}", reviewList);
		for (int j = 0; j < usageList.size(); j++) {
			for (int i = 0; i < reviewList.size(); i++) {
				if (reviewList.get(i).getUsage_num() == usageList.get(j).getUsage_num()) {
					usageList.get(j).setIsReviewed(1);
					break;
				}
				usageList.get(j).setIsReviewed(0);
			}
		}

		// ????????? ?????? ???????????????
		log.debug("???????????? ????????? : {}", usageList);

		// ????????? ?????? html??? ????????????.

		// ?????? ????????? ?????? ??????????????? ???????????? ??? ???????????? ????????? ?????? ?????? ??????.
//		for(int i = 0; i < usageList.size(); i++) {
//			Restaurant_member restaurantMember = restaurantService.selectOne1(usageList.get(i).getRestaurant_num());
//			usageList.get(i).setRestaurant_name(restaurantMember.getRestaurant_name());
//		}

		model.addAttribute("member", member);
		model.addAttribute("usageList", usageList);
		return "userView/review";
	}

	// ?????? ?????????
	@GetMapping("insertReview")
	public String insertReview(int usageNum, Model model) {
		
		ArrayList<Tags> tagList = restaurantService.tagList("???");
		ArrayList<Tags> tagList2 = restaurantService.tagList("?????????");
		ArrayList<Tags> tagList3 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList4 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList5 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList6 = restaurantService.tagList("?????????");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);
		
		// ?????? ????????? ?????? ?????? ????????????
		Usage_history usage = service.selectOneUsageHistory(usageNum);
		log.debug(" {} ", usage);
		model.addAttribute("usage", usage);
		return "userView/insertReview";
		
	}
	// ???????????? form
//	@PostMapping("insertReview")
//	public String insertReview(Review review) {
//				
//		String title = "?????? ??????";
//		review.setTitle(title);
//		
//		//????????? ?????? ????????? ????????????.
//		int result = service.insertReview(review);
//		
//		
//		return "redirect:/mypage/insertReview?usageNum=" + review.getUsage_num();
//	}

	// ???????????? ajax
	@ResponseBody
	@GetMapping("createReview")
	public Review insertReviewAjax(Review review) {
		String title = "?????? ??????";
		review.setTitle(title);

		// ????????? ?????? ????????? ????????????.
		int result = service.insertReview(review);
		
		//?????? ??????, ?????? ????????? ?????? ??????
		Restaurant_member member = restaurantService.selectOne1(review.getRestaurant_num());
		double avr = (member.getRestaurant_grade() + review.getGrade()) / 2;
		member.setRestaurant_grade(avr);
		result = restaurantService.updateRest(member);

		return review;
	}

	// ?????? ?????? ?????????
	@GetMapping("introduce_store")
	public String introduceStore(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/introduce_store";
	}

	// ??????
	@GetMapping("couponandinquiry")
	public String couponandinqury(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/couponandinquiry";
	}

	// ??????????????????
	@GetMapping("seereservation")
	public String seeresevation(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			ArrayList<Reservation> reservationlist = service.seeAllReservation(user.getUsername());
			log.debug("??????????????? : {}", reservationlist);
			model.addAttribute("reservationlist", reservationlist);
			ArrayList<Reservation> lastreservationlist = service.seeAllLastReservation(user.getUsername());
			model.addAttribute("lastreservationlist", lastreservationlist);
			ArrayList<Reservation> cancelReservationList = restaurantService
					.seeAllCancelReservation(user.getUsername());
			model.addAttribute("cancelReservationList", cancelReservationList);
		}
		return "userView/seereservation";
	}

	// ?????? ????????????
	@GetMapping("seeReservationDetail")
	public String seeReservationDetail(int reservation_num, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("??? : {} ", reservation_num);
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			Reservation reservation = restaurantService.reservationSelect(reservation_num);
			log.debug("??????????????? : {}", reservation);
			model.addAttribute("reservation", reservation);
		}
		return "userView/seeReservationDetail";
	}

	// ????????????
	@GetMapping("notice")
	public String notice(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		List<Web_board> notice = service.noticeBoard();
		model.addAttribute("notice", notice);
		return "userView/notice";
	}

	//??????????????????
	@GetMapping("noticeread")
	public String noticeread(int board_num, Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		Web_board noticeRead = service.noticeRead(board_num);
		model.addAttribute("noticeRead", noticeRead);
		return "userView/notice2";
	}

	// ???
	@GetMapping("mywishlist")
	public String mywishlist(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			ArrayList<Restaurant_zzim> mywishlist = service.mywishlist(user.getUsername());
			log.debug("??? : {}", mywishlist);
			model.addAttribute("mywishlist", mywishlist);

		}
		return "userView/mywishlist";
	}

	// ????????? ?????? ????????????
	@GetMapping("profile")
	public String profile(int member_num, Model model, HttpServletResponse response,
			@AuthenticationPrincipal UserDetails user) {
		log.debug("profile_member_num: {}", member_num);
		log.debug("profile_response: {}", response);
		// ????????? ??? ????????? ??? ?????? ??????
		User_member member = service.selectUser(user.getUsername());
		// ????????? ?????????
		String originalfile = new String(member.getMember_originalfile());
		try {
			response.setHeader("Content-Disposition",
					" attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// ????????? ?????? ??????
		String fullPath = uploadPath + "/" + member.getMember_savedfile();
		// ????????? ????????? ?????? ?????? ???????????? ????????????????????? ????????? ???????????????
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			// Spring??? ?????? ?????? ?????? ???????????? ??????
			FileCopyUtils.copy(filein, fileout);
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}

	// ?????????????????? ???????????? ??????
	@GetMapping("myinfomodify")
	public String myinfomodify(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		ArrayList<Tags> tagList = restaurantService.tagList("???");
		ArrayList<Tags> tagList2 = restaurantService.tagList("?????????");
		ArrayList<Tags> tagList3 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList4 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList5 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList6 = restaurantService.tagList("?????????");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);
		
		return "userView/myinfomodify";
	}

	// ???????????? ?????? ??????
	@PostMapping("myinfomodify")
	public String myinfomodify(User_member member, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
		log.debug("????????? ?????? : {}", member);
		/*
		 * User_member oldmember = null; String oldSavedfile = null; String savedfile =
		 * null;
		 * 
		 * //??????????????? ?????? ?????? ???????????? ?????? ??? ??? ?????? ?????? if (upload != null && !upload.isEmpty()) {
		 * oldmember = service.selectUser(user.getUsername()); log.debug("1");
		 * oldSavedfile = oldmember == null ? null : oldmember.getMember_savedfile();
		 * log.debug("1");
		 * 
		 * savedfile = FileService.saveFile(upload, uploadPath); log.debug("1");
		 * member.setMember_originalfile(upload.getOriginalFilename()); log.debug("1");
		 * member.setMember_savedfile(savedfile); log.debug("?????????:{}, ?????????:{}", savedfile,
		 * oldSavedfile); }
		 */
		member.setMember_id(user.getUsername());
		int result = service.updateUser(member);
		// ??? ?????? ?????? and ????????? ????????? ?????? ?????? ????????? ??????
		/*
		 * if (result == 1 && savedfile != null) { FileService.deleteFile(uploadPath +
		 * "/" + oldSavedfile); }
		 */
		return "redirect:";
	}

	// ????????????
	@GetMapping("mybenefit")
	public String mybenefit(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			log.debug("?????? ??????:{}", member);
		}
		return "userView/mybenefit";
	}

	// ????????????
	@GetMapping("leaveId")
	public String leaveId(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "userView/leaveId";
	}

	// ????????????
	@PostMapping("/leaveId")
	public String leaveId(@AuthenticationPrincipal UserDetails user) {
		User_member member = service.selectUser(user.getUsername());
		int result = service.deleteUser(member.getMember_id());
		return "redirect:/logout";
	}

	// ??????????????????
	@GetMapping("restaurantMain")
	public String restaurantMain(@AuthenticationPrincipal UserDetails user, Model model) {
		if(user == null) {
			return "redirect:/";
		}
		
		User_member member = service.selectUser(user.getUsername());
		Restaurant_member restaurant = restaurantService.selectOne(member.getMember_id());
		int restaurant_num = restaurant.getRestaurant_num();
		log.debug("{}", restaurant_num);
		log.debug("{}", member);
		//??????, ????????????, ????????? ????????? ?????????
		Restaurant_Graphs graphData1 = restaurantService.selectRestaurantData(0, restaurant_num);
		Restaurant_Graphs graphData2 = restaurantService.selectRestaurantData(-1, restaurant_num);
		Restaurant_Graphs graphData3 = restaurantService.selectRestaurantData(-2, restaurant_num);
		Restaurant_Graphs graphData4 = restaurantService.selectRestaurantData(-3, restaurant_num);
		Restaurant_Graphs graphData5 = restaurantService.selectRestaurantData(-4, restaurant_num);
		
		model.addAttribute("day1", graphData1);
		model.addAttribute("day2", graphData2);
		model.addAttribute("day3", graphData3);
		model.addAttribute("day4", graphData4);
		model.addAttribute("day5", graphData5);
		
		return "/restaurantView/restaurantMain";
	}

	// restMemberMain - ??????????????????
	@GetMapping("restaurantRTMemberMain")
	public String restaurantRTMemberMain(Model model, @AuthenticationPrincipal UserDetails user) {

		Restaurant_member member = restaurantService.selectOne(user.getUsername());
		ArrayList<Reservation> reservationList = restaurantService.ReservationList(member.getRestaurant_num());
		log.debug("{}", reservationList);

		model.addAttribute("reservationList", reservationList);
		return "/restaurantView/restaurantRTMemberMain";
	}

	// rsetreview - ????????????
	@GetMapping("rsetreview")
	public String rsetreview(Model model, @AuthenticationPrincipal UserDetails user) {

		Restaurant_member member = restaurantService.selectOne(user.getUsername());
		ArrayList<Menu> menuList = restaurantService.menucheck(member.getRestaurant_num());

		ArrayList<Tags> tagList = restaurantService.tagList("???");
		ArrayList<Tags> tagList2 = restaurantService.tagList("?????????");
		ArrayList<Tags> tagList3 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList4 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList5 = restaurantService.tagList("??????");
		ArrayList<Tags> tagList6 = restaurantService.tagList("?????????");
		log.debug("?????? : {}", member);
		log.debug("??????????????? : {}", menuList);
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);
		model.addAttribute("member", member);
		model.addAttribute("menuList", menuList);

		return "/restaurantView/rsetreview";
	}

	//??????????????????
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

	//??????????????????
	@GetMapping("inquirywrite")
	public String inquirywrite(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "/userView/inquiryWrite";
	}

	//??????????????????
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
		// ????????? ????????? ????????? ?????? sql?????? ????????? ?????? ??????
		// ????????? ?????? ????????? ??????
		// inputiryboard.html??? ????????????.
		return "/userView/inquiryRead";
	}

	// ????????????
	@GetMapping("cancelReservation")
	public String cancelReservation(int reservation_num) {
		log.debug("{} : ", reservation_num);
		Reservation reservation = restaurantService.reservationSelect(reservation_num);
		Restaurant_member member = restaurantService.selectOne1(reservation.getRestaurant_num());
		int people = member.getRestaurant_people() + reservation.getReservation_people();
		member.setRestaurant_people(people);
		restaurantService.peopleCount(member);
		int result = restaurantService.cancelReservation(reservation_num);
		return "redirect:/mypage/seereservation";
	}

	
	//???????????? ?????? Action
	@PostMapping("submitWebBoard")
	public String submitWebBoard(Web_board b) {
		log.debug("{}", b);

		int result = service.insertBoard(b);
		return "redirect:/mypage/inquiryboard";
	}

	//??????????????? ????????????
	@ResponseBody
	@GetMapping("replyList")
	public List<Web_reply> replyList(int board_num) {
		log.debug("{}", board_num);
		List<Web_reply> replyList = service.readReply(board_num);
		return replyList;
	}

	//???????????? ????????????
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

	//???????????? ?????? Action
	@PostMapping("inquirymodifyAction")
	public String inquirymodifyAction(Web_board b, Model m) {
		log.debug("{}", b);
		int result = service.updateBoard(b);
		return "redirect:/userView/inquiryRead?board_num=" + b.getBoard_num();
	}

	//???????????? ?????????
	@ResponseBody
	@GetMapping("inquirydelete")
	public String inquiryDelete(int board_num) {
		log.debug("{}", board_num);
		int result = service.deleteBoard(board_num);
		return "redirect:/userView/inquiryBoard";
	}

}