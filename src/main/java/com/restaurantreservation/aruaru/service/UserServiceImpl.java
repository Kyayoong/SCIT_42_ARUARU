package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.UserDao;
import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;
import com.restaurantreservation.aruaru.domain.tabletest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원가입
	@Override
	public int insertUser(User_member member) {
		String encodePassword = passwordEncoder.encode(member.getMember_pw());
		member.setMember_pw(encodePassword);
		int result = dao.insertUser(member);
		return result;
	}

	@Override
	public boolean idcheck(String member_id) {
		User_member member = dao.selectOne(member_id);
		return member == null ? true : false;
	}

	@Override
	public User_member selectUser(String member_id) {
		return dao.selectOne(member_id);
	}

	@Override
	public int updateUser(User_member member) {
		if (member.getMember_pw() != null && member.getMember_pw().length() != 0) {
			String encodedPassword = passwordEncoder.encode(member.getMember_pw());
			member.setMember_pw(encodedPassword);
		}
		int result = dao.updateUser(member);
		return result;
	}

	@Override
	public int deleteUser(String member_id) {
		int result = dao.deleteUser(member_id);
		return result;
	}

	@Override
	public List<Web_board> findBoard(String userid) {
		// TODO Auto-generated method stub
		List<Web_board> findById = dao.findBoardById(userid);
		return findById;
	}

	@Override
	public int insertBoard(Web_board b) {
		// TODO Auto-generated method stub
		int result = dao.insertBoard(b);
		return result;
	}

	@Override
	public Web_board readBoard(int board_num) {
		// TODO Auto-generated method stub
		Web_board b = dao.findBoard(board_num);
		return b;
	}

	// 특정 회원의 이용 내역 불러오기
	@Override
	public ArrayList<Usage_history> selectAllUsageHistory(String username) {
		ArrayList<Usage_history> list = dao.selectAllUsageHistory(username);
		return list;
	}


	// 예약내역 확인하기
	@Override
	public ArrayList<Reservation> seeAllReservation(String member_id) {
		ArrayList<Reservation> reservationlist = dao.seeAllReservation(member_id);
		return reservationlist;
	}

	
	@Override
	public ArrayList<Reservation> seeAllLastReservation(String member_id) {
		ArrayList<Reservation> lastreservationlist = dao.seeAllLastReservation(member_id);
		return lastreservationlist;
	}

	// 특정 이용내역 받아오기
	@Override
	public Usage_history selectOneUsageHistory(int usageNum) {
		Usage_history usage = dao.selectOneUsageHistory(usageNum);
		return usage;
	}


	@Override
	public List<Web_reply> readReply(int board_num) {
		// TODO Auto-generated method stub
		List<Web_reply> replyList = dao.readReply(board_num);
		return replyList;
	}

	@Override
	public List<Web_reply> readReplyAll(int board_num) {
		// TODO Auto-generated method stub
		List<Web_reply> replyListAll = dao.readReply(board_num); 
		return replyListAll;
	}
	
	//리뷰 객체 저장하기.
	@Override
	public int insertReview(Review review) {
		int result = dao.insertReview(review);
		return result;
	}


	@Override
	public int updateBoard(Web_board b) {
		// TODO Auto-generated method stub
		int result = dao.updateBoard(b);
		return result;
	}

	@Override
	public int deleteBoard(int board_num) {
		// TODO Auto-generated method stub
		int result = dao.deleteBoard(board_num);
		return result;
	}

	public ArrayList<Review> selectAllReview(String username) {
		ArrayList<Review> list = dao.selectAllReview(username);
		return list;

	}

	@Override
	public String ownTags(String username) {
		// TODO Auto-generated method stub
		String mytags = dao.myTags(username);
		return mytags;
	}

	@Override
	public List<Integer> recommend(String[] mytags) {
		// TODO Auto-generated method stub
		List<Integer> stores = dao.recommend(mytags);
		return stores;
	}

	@Override
	public List<Restaurant_member> recommendStores(int[] a) {
		// TODO Auto-generated method stub
		List<Restaurant_member> rrmm = dao.recommendStores(a);
		return rrmm;
	}

	@Override
	public int updateRole(String member_id) {
		int result = dao.updateRole(member_id);
		return result;
	}
	
	//공지글 입력
	@Override
	public int insertNoticeBoard(Web_board notice) {
		int result = dao.insertNoticeBoard(notice);
		return result;
	}




}
