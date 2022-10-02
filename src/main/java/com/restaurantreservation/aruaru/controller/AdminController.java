package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.domain.Admin_Graphs;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.service.AdminService;
import com.restaurantreservation.aruaru.service.HomeService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin")
@Slf4j
@Controller
public class AdminController {
	@Autowired AdminService service;
	@Autowired HomeService homeService;
	
	@GetMapping("main")
	public String main(Model model) {
		//리뷰 개수, 일일 방문자 수를 가져온다.(오늘 = 0, 어제 = -1 ... -4까지
		Admin_Graphs graphData1 = homeService.selectData(0);
		Admin_Graphs graphData2 = homeService.selectData(-1);
		Admin_Graphs graphData3 = homeService.selectData(-2);
		Admin_Graphs graphData4 = homeService.selectData(-3);
		Admin_Graphs graphData5 = homeService.selectData(-4);
		
		log.debug("{}", graphData1);
		log.debug("{}", graphData2);
		log.debug("{}", graphData3);
		log.debug("{}", graphData4);
		log.debug("{}", graphData5);
		
		model.addAttribute("day1", graphData1);
		model.addAttribute("day2", graphData2);
		model.addAttribute("day3", graphData3);
		model.addAttribute("day4", graphData4);
		model.addAttribute("day5", graphData5);
		
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
		int replyFlag = 1;
		
		//모든 게시글 가져와서 뿌리기
		ArrayList<Web_board> normalList = service.normalBoardList();
		ArrayList<Web_board> noticeList = service.noticeBoardList();
		
		//답변완료된 문의글 리스트 가져오기
		ArrayList<Web_reply> replyList = service.allReplyList();
		if(replyList.isEmpty()) {
			log.debug("댓글이 없습니다.");
			replyFlag = 0;
		}
		//normalList의 web_board 객체 하나하나와 replyList의 web_reply객체를 하나하나 비교하여 답변이 있으면 해당 web_board에 답변여부 넣기
		for(int j = 0; j < normalList.size(); j++) {
			for(int i = 0; i < replyList.size(); i++) {
				if(replyList.get(i).getBoard_num() == normalList.get(j).getBoard_num()) {
					normalList.get(j).setReply_cnt(normalList.get(j).getReply_cnt() + 1);
				}
			}
		}
		model.addAttribute("normalList", normalList);
		model.addAttribute("noticeList", noticeList);
		
		return "/adminView/adminBoardMain";
	}
	
	//게시글 읽기
	@GetMapping("readBoard")
	public String readBoard(@RequestParam(name="boardNum", defaultValue = "0") int boardNum, 
							Model model) {
		//댓글이 있고 없고(있을 때, 없을 때 html내용이 바뀜)
		int replyFlag = 1;
		//번호를 통해 게시글 검색
		Web_board board = service.selectOneBoard(boardNum);
		
		//만약 글이 없을 때
		if(board == null) {
			return "redirect:/";
		}
		//board가 있다면 해당 글 조회수++(sql에서)
		int result = service.updateHitCnt(boardNum);
		
		//리플 가져오기. (먼저 댓글 있는지 확인 후, 있으면 가져올까? - 화면에 댓글 있으면 div내용이 바뀌어야하니까.)
		ArrayList<Web_reply> replyList = service.selectReplyList(boardNum);
		if(replyList.isEmpty()) {
			log.debug("댓글이 없습니다.");
			replyFlag = 0;
		}

		//board 다시 부르기
		board = service.selectOneBoard(boardNum);
		
		//해당 게시글 모델에 넣어 보내기
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyFlag", replyFlag);

		return "adminView/readBoard";
	}
	
	//게시글 리프레쉬
	@ResponseBody
	@GetMapping("listRefresh")
	public HashMap<Object, Object> listRefresh(){
		HashMap<Object, Object> list = new HashMap<>();
		
		ArrayList<Web_board> noticeList = service.noticeBoardList();
		ArrayList<Web_board> normalList = service.normalBoardList();
		
		//답변완료된 문의글 리스트 가져오기
		ArrayList<Web_reply> replyList = service.allReplyList();
		if(replyList.isEmpty()) {
			log.debug("댓글이 없습니다.");
		}
		//normalList의 web_board 객체 하나하나와 replyList의 web_reply객체를 하나하나 비교하여 답변이 있으면 해당 web_board에 답변여부 넣기
		for(int j = 0; j < normalList.size(); j++) {
			for(int i = 0; i < replyList.size(); i++) {
				if(replyList.get(i).getBoard_num() == normalList.get(j).getBoard_num()) {
					normalList.get(j).setReply_cnt(normalList.get(j).getReply_cnt() + 1);
				}
			}
		}
		
		
		
		list.put("notice", noticeList);
		list.put("normal", normalList);
		
		
		return list;
	}
	
	//추천수 올리기 ajax 처리
	@ResponseBody
	@GetMapping("suggestVote")
	public int suggestVote(@RequestParam(name="boardNum", defaultValue = "0") int boardNum) {
		int result = service.updateSuggestionCnt(boardNum);
		return result;
	}
	
	//추천수 리프레쉬
	@ResponseBody
	@GetMapping("suggestRefresh")
	public int suggestRefresh(@RequestParam(name="boardNum", defaultValue = "0") int boardNum){
		int result = service.selectBoardSuggestion(boardNum);
		return result;
	}
	
	//문의글 답변 달기
	@PostMapping("insertReply")
	public String insertReply(@AuthenticationPrincipal UserDetails user,
							Web_reply reply) {
		//넘어온 reply객체에 로그인한(댓글작성자) set
		reply.setMember_id(user.getUsername());
		
		//set 완료된 댓글을 sql로 db에 저장
		int result = service.insertReply(reply);
		return "redirect:/admin/readBoard?boardNum=" + reply.getBoard_num();
	}
	
	//문의글 답변 시, 내용에 따른 등록 버튼 활성화
	@ResponseBody
	@PostMapping("contentsValidation")
	public int chech(String replyContents) {
		int cnt = 0;
		cnt = replyContents.length();
		return cnt;
	}
	
}
