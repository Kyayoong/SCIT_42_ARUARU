package com.restaurantreservation.aruaru.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;

@Mapper
public interface AdminDao {
	//일반 게시글 가져오기
	ArrayList<Web_board> normalBoardList();
	//공지 게시글 가져오기
	ArrayList<Web_board> noticeBoardList();
	//특정 게시글 하나 가져오기
	Web_board selectOneBoard(int boardNum);
	//특정 게시글 조회수 올리기
	int updateHitCnt(int boardNum);
	
	//추천수 불러오기
	int selectBoardSuggestion(int boardNum);
	//추천수 올리기
	int updateSuggestionCnt(int boardNum);
	
	//댓글 달기
	int insertReply(Web_reply reply);
	
	//댓글 가져오기
	ArrayList<Web_reply> selectReplyList(int boardNum);
	
	//전체 답변개수
	ArrayList<Web_reply> allReplyList();

}
