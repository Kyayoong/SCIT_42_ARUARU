package com.restaurantreservation.aruaru.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Web_board;

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

}
