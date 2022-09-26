package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.AdminDao;
import com.restaurantreservation.aruaru.domain.Web_board;

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
	
	
}
