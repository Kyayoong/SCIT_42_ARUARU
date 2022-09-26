package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin")
@Slf4j
@Controller
public class AdminController {
	@Autowired AdminService service;
	
	@GetMapping("main")
	public String main() {
		return "/adminView/adminMain";
	}
	
	//restMemberMain - 식당멤버관리창
	@GetMapping("restMemberMain")
	public String restMemberMain() {
		return "/adminView/adminRTMemberMain";
	}
	
	//genMemberMain - 일반회원관리창
	@GetMapping("genMemberMain")
	public String genMemberMain() {
		return "/adminView/adminGNMemberMain";
	}
	
	//boardMain - 게시글관리창
	@GetMapping("boardMain")
	public String boardMain(Model model) {
		//모든 게시글 가져와서 뿌리기
		ArrayList<Web_board> normalList = service.normalBoardList();
		ArrayList<Web_board> noticeList = service.noticeBoardList();
		
		log.debug("넘어온 일반글 리스트 : {}", normalList);
		log.debug("넘어온 공지글 리스트 : {}", noticeList);
		
		model.addAttribute("normalList", normalList);
		model.addAttribute("noticeList", noticeList);
		
		return "/adminView/adminBoardMain";
	}
	
	//게시글 읽기
	@GetMapping("readBoard")
	public String readBoard(@RequestParam(name="boardNum", defaultValue = "0") int boardNum, 
							Model model) {
		
		//번호를 통해 게시글 검색
		Web_board board = service.selectOneBoard(boardNum);
		
		//만약 글이 없을 때
		if(board == null) {
			log.debug("글 없음!");
			return "redirect:/";
		}
		
		//board가 있다면 해당 글 조회수++(sql에서)
		int result = service.updateHitCnt(boardNum);
		
		
		//board 다시 부르기
		board = service.selectOneBoard(boardNum);
		
		
		log.debug("넘어온 게시글 정보 : {}", board);
		
		//해당 게시글 모델에 넣어 보내기
		model.addAttribute("board", board);
		return "adminView/readBoard";
	}
	
	//게시글 리프레쉬
	@ResponseBody
	@GetMapping("listRefresh")
	public HashMap<Object, Object> listRefresh(){
		HashMap<Object, Object> list = new HashMap<>();
		
		ArrayList<Web_board> noticeList = service.noticeBoardList();
		ArrayList<Web_board> normalList = service.normalBoardList();
		
		list.put("notice", noticeList);
		list.put("normal", normalList);
		
		
		return list;
	}
}
