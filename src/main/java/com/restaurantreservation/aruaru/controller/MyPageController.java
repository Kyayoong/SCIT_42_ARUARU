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
			ArrayList<Reservation> cancelReservationList = restaurantService
					.seeAllCancelReservation(user.getUsername());
			model.addAttribute("cancelReservationList", cancelReservationList);
			ArrayList<Restaurant_zzim> mywishlist = service.mywishlist(user.getUsername());
			log.debug("찜 : {}", mywishlist);
			model.addAttribute("mywishlist", mywishlist);
			
		} else {
			model.addAttribute("member_nickname", "없음");
		}
		return "userView/mypage";
	}

	// 예약내역->리뷰선택창
	@GetMapping("review")
	public String review(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user == null) {
			return "redirect:/";
		}
		User_member member = service.selectUser(user.getUsername());
		// 계정정보를 통해 해당 아이디를 가진 이용내역을 다 가져온다.(실제로 간 기록이 있는 경우의 데이터만)
		ArrayList<Usage_history> usageList = service.selectAllUsageHistory(user.getUsername());
		// 해당 아이디의 모든 리뷰를 가져온다
		ArrayList<Review> reviewList = service.selectAllReview(user.getUsername());
		// UsageHistory에서 num을 참고하여 리뷰리스트를 확인한다. 리뷰 리스트에 해당 넘버가 있으면 작성완료
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

		// 없으면 작성 버튼활성화
		log.debug("사용내역 리스트 : {}", usageList);

		// 모델에 담아 html에 가져간다.

		// 식당 번호를 통해 식당이름을 가져와서 각 이용내역 객체에 식당 이름 저장.
//		for(int i = 0; i < usageList.size(); i++) {
//			Restaurant_member restaurantMember = restaurantService.selectOne1(usageList.get(i).getRestaurant_num());
//			usageList.get(i).setRestaurant_name(restaurantMember.getRestaurant_name());
//		}

		model.addAttribute("member", member);
		model.addAttribute("usageList", usageList);
		return "userView/review";
	}

	// 리뷰 입력창
	@GetMapping("insertReview")
	public String insertReview(int usageNum, Model model) {
		
		ArrayList<Tags> tagList = restaurantService.tagList("맛");
		ArrayList<Tags> tagList2 = restaurantService.tagList("서비스");
		ArrayList<Tags> tagList3 = restaurantService.tagList("인기");
		ArrayList<Tags> tagList4 = restaurantService.tagList("가격");
		ArrayList<Tags> tagList5 = restaurantService.tagList("계절");
		ArrayList<Tags> tagList6 = restaurantService.tagList("분위기");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);
		
		// 해당 번호의 이용 내역 받아오기
		Usage_history usage = service.selectOneUsageHistory(usageNum);
		log.debug(" {} ", usage);
		model.addAttribute("usage", usage);
		return "userView/insertReview";
		
	}
	// 리뷰입력 form
//	@PostMapping("insertReview")
//	public String insertReview(Review review) {
//				
//		String title = "아무 제목";
//		review.setTitle(title);
//		
//		//새로운 리뷰 객체를 저장한다.
//		int result = service.insertReview(review);
//		
//		
//		return "redirect:/mypage/insertReview?usageNum=" + review.getUsage_num();
//	}

	// 리뷰입력 ajax
	@ResponseBody
	@GetMapping("createReview")
	public Review insertReviewAjax(Review review) {
		String title = "아무 제목";
		review.setTitle(title);

		// 새로운 리뷰 객체를 저장한다.
		int result = service.insertReview(review);
		
		//저장 될때, 해당 가게의 평점 갱신
		Restaurant_member member = restaurantService.selectOne1(review.getRestaurant_num());
		double avr = (member.getRestaurant_grade() + review.getGrade()) / 2;
		member.setRestaurant_grade(avr);
		result = restaurantService.updateRest(member);

		return review;
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
			ArrayList<Reservation> cancelReservationList = restaurantService
					.seeAllCancelReservation(user.getUsername());
			model.addAttribute("cancelReservationList", cancelReservationList);
		}
		return "userView/seereservation";
	}

	// 예약 상세정보
	@GetMapping("seeReservationDetail")
	public String seeReservationDetail(int reservation_num, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("넘 : {} ", reservation_num);
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			Reservation reservation = restaurantService.reservationSelect(reservation_num);
			log.debug("리스트에여 : {}", reservation);
			model.addAttribute("reservation", reservation);
		}
		return "userView/seeReservationDetail";
	}

	// 공지사항
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

	//공지사항읽기
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

	// 찜
	@GetMapping("mywishlist")
	public String mywishlist(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
			ArrayList<Restaurant_zzim> mywishlist = service.mywishlist(user.getUsername());
			log.debug("찜 : {}", mywishlist);
			model.addAttribute("mywishlist", mywishlist);

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
	}

	// 회원정보변경 화면으로 이동
	@GetMapping("myinfomodify")
	public String myinfomodify(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		ArrayList<Tags> tagList = restaurantService.tagList("맛");
		ArrayList<Tags> tagList2 = restaurantService.tagList("서비스");
		ArrayList<Tags> tagList3 = restaurantService.tagList("인기");
		ArrayList<Tags> tagList4 = restaurantService.tagList("가격");
		ArrayList<Tags> tagList5 = restaurantService.tagList("계절");
		ArrayList<Tags> tagList6 = restaurantService.tagList("분위기");
		model.addAttribute("tagList", tagList);
		model.addAttribute("tagList2", tagList2);
		model.addAttribute("tagList3", tagList3);
		model.addAttribute("tagList4", tagList4);
		model.addAttribute("tagList5", tagList5);
		model.addAttribute("tagList6", tagList6);
		
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
	public String restaurantMain(@AuthenticationPrincipal UserDetails user, Model model) {
		if(user == null) {
			return "redirect:/";
		}
		
		User_member member = service.selectUser(user.getUsername());
		Restaurant_member restaurant = restaurantService.selectOne(member.getMember_id());
		int restaurant_num = restaurant.getRestaurant_num();
		log.debug("{}", restaurant_num);
		log.debug("{}", member);
		//예약, 리뷰개수, 찜개수 그래프 데이터
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

	// restMemberMain - 식당관리화면
	@GetMapping("restaurantRTMemberMain")
	public String restaurantRTMemberMain(Model model, @AuthenticationPrincipal UserDetails user) {

		Restaurant_member member = restaurantService.selectOne(user.getUsername());
		ArrayList<Reservation> reservationList = restaurantService.ReservationList(member.getRestaurant_num());
		log.debug("{}", reservationList);

		model.addAttribute("reservationList", reservationList);
		return "/restaurantView/restaurantRTMemberMain";
	}

	// rsetreview - 리뷰관리
	@GetMapping("rsetreview")
	public String rsetreview(Model model, @AuthenticationPrincipal UserDetails user) {

		Restaurant_member member = restaurantService.selectOne(user.getUsername());
		ArrayList<Menu> menuList = restaurantService.menucheck(member.getRestaurant_num());

		ArrayList<Tags> tagList = restaurantService.tagList("맛");
		ArrayList<Tags> tagList2 = restaurantService.tagList("서비스");
		ArrayList<Tags> tagList3 = restaurantService.tagList("인기");
		ArrayList<Tags> tagList4 = restaurantService.tagList("가격");
		ArrayList<Tags> tagList5 = restaurantService.tagList("계절");
		ArrayList<Tags> tagList6 = restaurantService.tagList("분위기");
		log.debug("맴버 : {}", member);
		log.debug("메뉴리스트 : {}", menuList);
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

	//문의사항목록
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

	//문의사항쓰기
	@GetMapping("inquirywrite")
	public String inquirywrite(Model model, @AuthenticationPrincipal UserDetails user) {
		if (user != null) {
			User_member member = service.selectUser(user.getUsername());
			model.addAttribute("member", member);
		}
		return "/userView/inquiryWrite";
	}

	//문의사항읽기
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

	// 예약취소
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

	
	//문의사항 등록 Action
	@PostMapping("submitWebBoard")
	public String submitWebBoard(Web_board b) {
		log.debug("{}", b);

		int result = service.insertBoard(b);
		return "redirect:/mypage/inquiryboard";
	}

	//문의사항에 리플목록
	@ResponseBody
	@GetMapping("replyList")
	public List<Web_reply> replyList(int board_num) {
		log.debug("{}", board_num);
		List<Web_reply> replyList = service.readReply(board_num);
		return replyList;
	}

	//문의사항 수정하기
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

	//문의사항 수정 Action
	@PostMapping("inquirymodifyAction")
	public String inquirymodifyAction(Web_board b, Model m) {
		log.debug("{}", b);
		int result = service.updateBoard(b);
		return "redirect:/userView/inquiryRead?board_num=" + b.getBoard_num();
	}

	//문의사항 지우기
	@ResponseBody
	@GetMapping("inquirydelete")
	public String inquiryDelete(int board_num) {
		log.debug("{}", board_num);
		int result = service.deleteBoard(board_num);
		return "redirect:/userView/inquiryBoard";
	}

}