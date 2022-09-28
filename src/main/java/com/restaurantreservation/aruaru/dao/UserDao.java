package com.restaurantreservation.aruaru.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.domain.tabletest;

@Mapper
public interface UserDao {
	// 회원가입
	int insertUser(User_member member);

	// ID 개수 확인
	public int countMemberid(String member_id);

	// 정보 가져오기
	User_member selectOne(String member_id);

	// 정보 수정
	public int updateUser(User_member member);
	
	// 회원 탈퇴
	public int deleteUser(String member_id);

	List<Web_board> findBoardById(String username);

	int insertBoard(Web_board b);

	public Web_board findBoard(int board_num);

	int insertReply(Web_reply r);

	List<Web_reply> readReply(int board_num);

	int replyDelete(int reply_num);
}
