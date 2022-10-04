package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;

import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;

public interface AdminService {
	
	/**
	 * 일반 게시글 전체 가져오기
	 * @return 일반 게시글 리스트
	 */
	ArrayList<Web_board> normalBoardList();
	
	/**
	 * 공지게시글 가져오기
	 * @return 공지 게시글 리스트
	 */
	ArrayList<Web_board> noticeBoardList();
	
	/**
	 * 특정 게시글 가져오기
	 * @param 가져올 게시글의 글번호
	 * @return 게시글 객체
	 */
	Web_board selectOneBoard(int boardNum);

	/**
	 * 조회수 올리는 메소드
	 * @param boardNum 게시글 번호
	 * @return 성공여부
	 */
	int updateHitCnt(int boardNum);
	
	/**
	 * 추천수 갱신
	 * @param boardNum 갱신할 게시글 번호
	 * @return 갱신된 추천수
	 */
	int selectBoardSuggestion(int boardNum);
	
	/**
	 * 추천수 올리기
	 * @param boardNum 추천수 올릴 게시글 번호
	 * @return 성공여부
	 */
	int updateSuggestionCnt(int boardNum);
	
	/**
	 * 문의글 댓글 저장
	 * @param reply 댓글 객체
	 * @return 성공여부
	 */
	int insertReply(Web_reply reply);
	
	/**
	 * 특정 문의글 댓글 목록 가져오기
	 * @param boardNum 문의글 번호
	 * @return 댓글 리스트
	 */
	ArrayList<Web_reply> selectReplyList(int boardNum);
	
	/**
	 * 전체 답변글 가져오기
	 * @return
	 */
	ArrayList<Web_reply> allReplyList();
	
	/**
	 * 승인되지 않은 레스토랑 멤버 가져오기
	 * @return
	 */
	ArrayList<Restaurant_member> selectNotCertificatedMember();
	
	/**
	 * 레스토랑 승인하기
	 * @param restaurant_num
	 * @return
	 */
	int acceptCertificationByNum(int restaurant_num);
	
	/**
	 * 레스토랑 거절됨
	 * @param restaurant_num
	 * @return
	 */
	int rejectCertificationByNum(int restaurant_num);
	
	/**
	 * 레스토랑 등록 재고
	 * @param restaurant_num
	 * @return
	 */
	int reconsiderCertifiacationByNum(int restaurant_num); 
}
