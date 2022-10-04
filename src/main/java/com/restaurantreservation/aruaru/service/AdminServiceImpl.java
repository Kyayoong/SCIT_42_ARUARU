package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.AdminDao;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired AdminDao dao;
	
	//일반 게시글 목록 가져오기
	@Override
	public ArrayList<Web_board> normalBoardList() {
		ArrayList<Web_board> normalList = dao.normalBoardList(); 
		return normalList;
	}
	
	//공지 게시글 목록 가져오기
	@Override
	public ArrayList<Web_board> noticeBoardList() {
		ArrayList<Web_board> noticeList = dao.noticeBoardList();
		return noticeList;
	}
	
	//특정 게시글 하나 가져오기
	@Override
	public Web_board selectOneBoard(int boardNum) {
		Web_board board = dao.selectOneBoard(boardNum);
		
		return board;
	}
	
	//특정 게시물 조회수 올리기
	@Override
	public int updateHitCnt(int boardNum) {
		int result = dao.updateHitCnt(boardNum);
		return result;
	}
	
	//문의글 불러오기 갱신하기
	@Override
	public int selectBoardSuggestion(int boardNum) {
		int suggestionCnt = dao.selectBoardSuggestion(boardNum);
		return suggestionCnt;
	}
	
	//문의글 추천수 올리기
	@Override
	public int updateSuggestionCnt(int boardNum) {
		int result = dao.updateSuggestionCnt(boardNum);
		return result;
	}
	
	//문의글 댓글달기
	@Override
	public int insertReply(Web_reply reply) {
		int result = dao.insertReply(reply);
		return result;
	}
	
	//문의글 댓글 가져오기
	@Override
	public ArrayList<Web_reply> selectReplyList(int boardNum) {
		ArrayList<Web_reply> list = dao.selectReplyList(boardNum);
		return list;
	}
	
	//전체 댓글 가져오기
	@Override
	public ArrayList<Web_reply> allReplyList() {
		ArrayList<Web_reply> list = dao.allReplyList();
		
		return list;
	}
	
	//승인되지 않은 레스토랑 인원들 가져오기
	@Override
	public ArrayList<Restaurant_member> selectNotCertificatedMember() {
		ArrayList<Restaurant_member> list = dao.selectNotCertificatedMember();
		return list;
	}
	
	//레스토랑 승인
	@Override
	public int acceptCertificationByNum(int restaurant_num) {
		int result = dao.acceptCertificationByNum(restaurant_num);
		return result;
	}
	
	//레스토랑 미승인
	@Override
	public int rejectCertificationByNum(int restaurant_num) {
		int result = dao.rejectCertificationByNum(restaurant_num);
		return result;
	}
	
	//레스토랑 거절 재고
	@Override
	public int reconsiderCertifiacationByNum(int restaurant_num) {
		int result = dao.reconsiderCertifiacationByNum(restaurant_num);
		return result;
	}
	
	
}
