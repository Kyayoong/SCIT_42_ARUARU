package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;

import com.restaurantreservation.aruaru.domain.Web_board;

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
}
