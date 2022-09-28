package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.List;

import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.domain.tabletest;

public interface UserService {
	// 회원가입
	public int insertUser(User_member member);

	// ID 중복확인
	public boolean idcheck(String member_id);

	// 회원 정보 가져옹기
	public User_member selectUser(String member_id);

	// 정보 수정
	public int updateUser(User_member member);

	// 회원 탈퇴
	public int deleteUser(String member_id);

	public List<Web_board> findBoard(String username);

	public int insertBoard(Web_board b);

	public Web_board readBoard(int board_num);

	// 예약내역 확인
	public ArrayList<Reservation> seeAllReservation(String member_id);

	/**
	 * 특정 회원의 이용내역 불러오기
	 * 
	 * @param 찾을 회원의 id 정보
	 * @return 이용내역 리스트
	 */
	public ArrayList<Usage_history> selectAllUsageHistory(String username);

	public int insertReply(Web_reply r);

	public List<Web_reply> readReply(int board_num);

	public List<Web_reply> readReplyAll(int board_num);

	public int replyDelete(int reply_num);

}
